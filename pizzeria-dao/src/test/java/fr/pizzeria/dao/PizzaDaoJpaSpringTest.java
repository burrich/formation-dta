package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import fr.pizzeria.config.SpringJpaConfig;

@ContextConfiguration(classes = SpringJpaConfig.class)
public class PizzaDaoJpaSpringTest extends PizzaDaoTest {
	
	@Autowired
	private void setPizzaDao(@Qualifier("pizzaDaoJpaSpring") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
