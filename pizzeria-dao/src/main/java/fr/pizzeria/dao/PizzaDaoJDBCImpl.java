package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.collections4.ListUtils;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBCImpl implements IPizzaDao {
	
	private String url;
	private String user;
	private String password;
	private Map<String, Pizza> pizzas = new HashMap<>();
	
	public PizzaDaoJDBCImpl(String driver, String url, String user, String password) throws DaoException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			throw new DaoException();
		}
		
		this.url = url;
		this.user = user;
		this.password = password;
		
		List<Pizza> pizzasList = findAllPizzas();
		if (pizzasList != null) {
			pizzasList.forEach(pizza -> pizzas.put(pizza.getCode(), pizza));
		}
	}
	
	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		//TODO: utiliser la map
		List<Pizza> pizzas = new ArrayList<>();
		
		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet resultats = statement.executeQuery("SELECT * FROM pizza");
		) {
			while (resultats.next()) {
				String code = resultats.getString("reference");
				String nom = resultats.getString("libelle");
				double prix = resultats.getDouble("prix");
				String categorieStr = resultats.getString("categorie");
				CategoriePizza categorie = CategoriePizza.valueOf(categorieStr);
				
				pizzas.add(new Pizza(code, nom, prix, categorie));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} 
		
		return pizzas;
	}

	@Override
	public boolean savePizza(Pizza newPizza) throws DaoException {
		try(Connection connection = getConnection()) {
			insertPizza(newPizza, connection);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
		return true;
	}
	
	public boolean saveAllPizzas(List<Pizza> pizzasFichier, int nbGroupe) throws DaoException {
		pizzasFichier.sort(Comparator.comparing(Pizza::getCode));
		List<List<Pizza>> listPartitionnee = ListUtils.partition(pizzasFichier, nbGroupe);
		
		try (Connection connection = getConnection()) {
			connection.setAutoCommit(false);
			insertListPizzas(listPartitionnee, connection);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
		return true;
	}
	
	

	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		String requete = 
				"UPDATE pizza " +
				"SET libelle = ?, prix = ?, categorie = ? " + 
				"WHERE reference = ?";
		
		try (Connection connection = getConnection();
			 PreparedStatement statement = (PreparedStatement) connection.prepareStatement(requete);
		) {
			statement.setString(1, updatePizza.getNom());
			statement.setDouble(2, updatePizza.getPrix());
			statement.setString(3, updatePizza.getCategorie().name());
			statement.setString(4, codePizza);
			statement.executeUpdate();
			
			pizzas.put(codePizza, updatePizza);
		} catch (SQLException e) {
			throw new DaoException();
		} 
		
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DaoException {
		String requete = 
				"DELETE from pizza " +
				"WHERE reference = ?";
		
		try (Connection connection = getConnection();
			 PreparedStatement statement = (PreparedStatement) connection.prepareStatement(requete);
		) {
			statement.setString(1, codePizza);
			statement.executeUpdate();
			
			pizzas.remove(codePizza);
		} catch (SQLException e) {
			throw new DaoException();
		} 
		
		return true;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	private void insertListPizzas(List<List<Pizza>> listListPizzas, Connection connection) throws SQLException, DaoException {
		for (List<Pizza> list3Pizzas : listListPizzas) {
			try {
				for (Pizza pizza : list3Pizzas) {
					insertPizza(pizza, connection);
				}
				
				connection.commit();
			} catch (DaoException e) {
				throw e;
			}
		}
	}
	
	private void insertPizza(Pizza pizza, Connection connection) throws DaoException {
		String requete = 
				"INSERT INTO pizza(libelle, reference, prix, categorie) " +
				"VALUES(?, ?, ?, ?)";
		
		try (PreparedStatement statement = (PreparedStatement) connection.prepareStatement(requete)){
			statement.setString(1, pizza.getNom());
			statement.setString(2, pizza.getCode());
			statement.setDouble(3, pizza.getPrix());
			statement.setString(4, pizza.getCategorie().name());
			
			int nbLignesAffectes = statement.executeUpdate();
			if (nbLignesAffectes == 0) {
				throw new SavePizzaException("Aucune ligne insérée en base de données");
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public Pizza findByCode(String code) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
