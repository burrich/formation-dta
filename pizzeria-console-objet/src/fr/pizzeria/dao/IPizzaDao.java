package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

/**
 * CRUD Pizza
 * @author Nicolas
 *
 */
public interface IPizzaDao {
	/**
	 * Retourne une copie du tableau de Pizza.
	 * @return tableau de Pizza
	 */
	Pizza[] findAllPizzas();
	
	/**
	 * Ajoute la pizza dans le tableau pizzas[].
	 * @param newPizza Objec Pizza
	 * @return R�sultat de l'op�ration
	 */
	boolean savePizza(Pizza newPizza);
	
	/**
	 * Mets � jour la Pizza dans le tableau pizzas[].
	 * @param codePizza Code de la Pizza
	 * @param updatePizza Objet Pizza
	 * @return R�sultat de l'op�ration
	 */
	boolean updatePizza(String codePizza, Pizza updatePizza);
	
	/**
	 * 
	 * @param codePizza Code de la Pizza
	 * @return R�sultat de l'op�ration
	 */
	boolean deletePizza(String codePizza);
}
