package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = SpringConfig.class)
public class PizzaDaoJDBCTemplateTest extends PizzaDaoTest {
	
	@Autowired
	private void setPizzaDao(PizzaDaoJDBCTemplate pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
