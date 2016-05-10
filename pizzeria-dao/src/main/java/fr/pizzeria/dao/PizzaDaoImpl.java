package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Impl�mentation de l'interface IPizzaDao.
 * @see IPizzaDao
 * @author Nicolas
 */
public class PizzaDaoImpl implements IPizzaDao {
	private Map<String, Pizza> pizzas = new HashMap<>();
	
	/**
	 * Initialisation de la map pizzas.
	 */
	public PizzaDaoImpl() {
		pizzas.put("PEP", new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzas.put("MAR", new Pizza("MAR", "Margherita", 14.50, CategoriePizza.SANS_VIANDE));
		pizzas.put("REIN", new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzas.put("FRO", new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzas.put("CAN", new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzas.put("SAV", new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzas.put("ORI", new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		pizzas.put("IND", new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
	}
	
	@Override
	public List<Pizza> findAllPizzas() {
		return new ArrayList<Pizza>(pizzas.values());
	}

	@Override
	public boolean savePizza(Pizza newPizza) throws DaoException {
		String codePizza = newPizza.getCode();
		
		if (pizzas.containsKey(codePizza)) {
			throw new SavePizzaException("code pizza déjà présent");
		}
		
		pizzas.put(codePizza, newPizza);
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException, NullPointerException {
		if (updatePizza == null) {
			throw new NullPointerException();
		}
		
		if (pizzas.containsKey(codePizza)) {
			pizzas.put(codePizza, updatePizza);
		} else {
			throw new UpdatePizzaException("code pizza non trouvé");
		}
		
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DaoException {
		if (pizzas.containsKey(codePizza)) {
			pizzas.remove(codePizza);
		} else {
			throw new DeletePizzaException("code pizza non trouvé");
		}
		
		return true;
	}
}
