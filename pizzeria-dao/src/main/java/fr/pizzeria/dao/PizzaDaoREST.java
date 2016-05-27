package fr.pizzeria.dao;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoREST implements IPizzaDao {
	private WebTarget target;

	public PizzaDaoREST() {
		Client client = ClientBuilder.newClient();
		target = client.target("htp://localhost:8080").path("api").path("pizzas");
	}
	
	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		Builder builder = target.request();
		Response response = builder.get();
		
		return null;
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