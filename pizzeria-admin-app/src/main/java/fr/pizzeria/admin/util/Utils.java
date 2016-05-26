package fr.pizzeria.admin.util;

import javax.enterprise.inject.Produces;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;

public class Utils {
	@Produces
	public static IPizzaDao getPizzaDao() {
		return new PizzaDaoImpl();
	}
}
