package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import fr.pizzeria.config.SpringJdbcConfig;

@ContextConfiguration(classes = SpringJdbcConfig.class)
public class PizzaDaoJdbcTemplateTest extends PizzaDaoTest {
	
	@Autowired
	private void setPizzaDao(@Qualifier("pizzaDaoJdbcTemplate") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
