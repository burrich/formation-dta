package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<Pizza> pizzas = null;
		
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultats = statement.executeQuery("SELECT * FROM pizza");
			
			while (resultats.next()) {
				if (pizzas == null) {
					pizzas = new ArrayList<>();
				}
				
				String code = resultats.getString("reference");
				String nom = resultats.getString("libelle");
				double prix = resultats.getDouble("prix");
				int categorieId = resultats.getInt("categorie_id") - 1;
				CategoriePizza categorie = CategoriePizza.values()[categorieId];
				
				pizzas.add(new Pizza(code, nom, prix, categorie));
			}
		} catch (SQLException e) {
			throw new DaoException();
		} 
		
		return pizzas;
	}

	@Override
	public boolean savePizza(Pizza newPizza) throws DaoException {
		String codePizza = newPizza.getCode();
		
		if (pizzas.containsKey(codePizza)) {
			throw new SavePizzaException("code pizza déjà présent");
		}
		
		try (Connection connection = getConnection()) {
			String requete = 
					"INSERT INTO pizza " +
					"VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(requete);
			statement.setNull(1, java.sql.Types.NULL);
			statement.setString(2, newPizza.getNom());
			statement.setString(3, newPizza.getCode());
			statement.setDouble(4, newPizza.getPrix());
			statement.setNull(5, java.sql.Types.NULL);
			statement.setInt(6, newPizza.getCategorie().ordinal() + 1);
			statement.executeUpdate();
			
			pizzas.put(codePizza, newPizza);
		} catch (SQLException e) {
			throw new DaoException();
		} 
		
		return true;
	}
	
	public boolean saveAllPizzas(List<Pizza> pizzas, int nbGroupe) throws DaoException {
		try (Connection connection = getConnection()) {
			connection.setAutoCommit(false);
			
			ListUtils.partition(pizzas, nbGroupe).forEach(pizzas3 -> {
				pizzas3.forEach(pizza -> {
					String codePizza = pizza.getCode();
					
					String requete = 
							"INSERT INTO pizza " +
							"VALUES(?, ?, ?, ?, ?, ?)";
					PreparedStatement statement;
					
					try {
						statement = (PreparedStatement) connection.prepareStatement(requete);
						statement.setNull(1, java.sql.Types.NULL);
						statement.setString(2, pizza.getNom());
						statement.setString(3, pizza.getCode());
						statement.setDouble(4, pizza.getPrix());
						statement.setNull(5, java.sql.Types.NULL);
						statement.setInt(6, pizza.getCategorie().ordinal() + 1);
						statement.executeUpdate();
					} catch (SQLException e) {
						try {
							connection.rollback();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
				
				});
				
				try {
					connection.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (SQLException e) {
			throw new DaoException();
		}
		
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		if (pizzas.containsKey(codePizza)) {
			try (Connection connection = getConnection()) {
				String requete = 
						"UPDATE pizza " +
						"SET libelle = ?, prix = ? " + 
						"WHERE reference = ?";
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(requete);
				statement.setString(1, updatePizza.getNom());
				statement.setDouble(2, updatePizza.getPrix());
				statement.setString(3, codePizza);
				statement.executeUpdate();
				
				pizzas.put(codePizza, updatePizza);
			} catch (SQLException e) {
				throw new DaoException();
			} 
		} else {
			throw new UpdatePizzaException("code pizza non trouvé");
		}
		
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DaoException {
		if (pizzas.containsKey(codePizza)) {
			try (Connection connection = getConnection()) {
				String requete = 
						"DELETE from pizza " +
						"WHERE reference = ?";
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(requete);
				statement.setString(1, codePizza);
				statement.executeUpdate();
				
				pizzas.remove(codePizza);
			} catch (SQLException e) {
				throw new DaoException();
			} 
		} else {
			throw new DeletePizzaException("code pizza non trouvé");
		}
		
		return true;
	}
	
	@Override
	public boolean importDonnees() {
		return true;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
