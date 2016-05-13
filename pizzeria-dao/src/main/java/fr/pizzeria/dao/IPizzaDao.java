package fr.pizzeria.dao;

import java.sql.SQLException;
import java.util.List;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * CRUD Pizza
 * @author Nicolas
 *
 */
public interface IPizzaDao {
	/**
	 * Retourne la liste des pizza
	 * @return Liste de pizza
	 * @throws DaoException 
	 * @throws SQLException 
	 */
	List<Pizza> findAllPizzas() throws DaoException;
	
	/**
	 * Ajoute la pizza dans le tableau pizzas[].
	 * @param newPizza Objec Pizza
	 * @return R�sultat de l'op�ration
	 */
	boolean savePizza(Pizza newPizza) throws DaoException;
	
	/**
	 * Mets � jour la Pizza dans le tableau pizzas[].
	 * @param codePizza Code de la Pizza
	 * @param updatePizza Objet Pizza
	 * @return R�sultat de l'op�ration
	 */
	boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException;
	
	/**
	 * 
	 * @param codePizza Code de la Pizza
	 * @return R�sultat de l'op�ration
	 */
	boolean deletePizza(String codePizza) throws DaoException;
}
