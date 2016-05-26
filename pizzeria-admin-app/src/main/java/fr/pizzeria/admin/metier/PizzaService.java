package fr.pizzeria.admin.metier;

import java.util.List;

import javax.inject.Inject;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class PizzaService {
	@Inject private IPizzaDao pizzaDao;
	
	public List<Pizza> findAllPizzas() throws DaoException {
		return pizzaDao.findAllPizzas();
	}

	public Pizza findByCode(String code) throws DaoException {
		return pizzaDao.findByCode(code);
	}
	
	public boolean savePizza(Pizza newPizza) throws DaoException {
		return pizzaDao.savePizza(newPizza);
	}
	
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		return pizzaDao.updatePizza(codePizza, updatePizza);
	}

	public boolean deletePizza(String codePizza) throws DaoException {
		return pizzaDao.deletePizza(codePizza);
	}
	
	public boolean saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {
		return pizzaDao.saveAllPizzas(listPizzas, nb);
	}
}
