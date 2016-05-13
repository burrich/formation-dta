package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pizzeria.exception.DaoException;
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
		pizzasList.forEach(pizza -> pizzas.put(pizza.getCode(), pizza));
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
			Statement statement = connection.createStatement();
			String requete = 
					"INSERT INTO pizza " +
					"VALUES(null, '" + newPizza.getNom() + "', '" + newPizza.getCode() + "', " + newPizza.getPrix() + ", null, " + newPizza.getCategorie().ordinal() + 1 + ")";
			int nbInsert = statement.executeUpdate(requete);
			
			if (nbInsert == 1) {
				pizzas.put(codePizza, newPizza);
			}
		} catch (SQLException e) {
			throw new DaoException();
		} 
		
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		
		if (updatePizza == null) {
			throw new NullPointerException();
		}
		
		if (pizzas.containsKey(codePizza)) {
			try (Connection connection = getConnection()) {
				Statement statement = connection.createStatement();
				String requete = 
						"UPDATE pizza " +
						"SET libelle = '" + updatePizza.getNom() + "', prix = " + updatePizza.getPrix() + " " + 
						"WHERE reference = '" + updatePizza.getCode() + "'"; 		
				int nbUpdate = statement.executeUpdate(requete);
				
				if (nbUpdate == 1) {
					pizzas.put(codePizza, updatePizza);
				}
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
		return false;
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
