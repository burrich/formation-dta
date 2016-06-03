package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import fr.pizzeria.config.SpringDataJpaConfig;

@ContextConfiguration(classes = SpringDataJpaConfig.class)
public class PizzaDaoDataJpaSpringTest extends PizzaDaoTest {
	
	@Autowired
	private void setPizzaDao(@Qualifier("pizzaDaoDataJpa") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
