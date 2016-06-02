package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
public class PizzaDaoDataJpa implements IPizzaDao {
	
	@Autowired private IPizzaRepository repo;
	
	@PersistenceContext private EntityManager em;

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		List<Pizza> pizzas = null;
		try {
			pizzas = repo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pizzas;
	}

	@Override
	public Pizza findByCode(String code) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean savePizza(Pizza newPizza) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
