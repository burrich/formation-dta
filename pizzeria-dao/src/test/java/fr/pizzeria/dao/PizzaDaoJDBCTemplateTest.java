package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;

public class PizzaDaoJDBCTemplateTest extends PizzaDaoTest {
	
	@Autowired
	private void setPizzaDao(PizzaDaoJDBCTemplate pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
