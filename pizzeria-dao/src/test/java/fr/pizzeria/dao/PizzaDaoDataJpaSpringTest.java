package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = SpringDataJpaConfig.class)
public class PizzaDaoDataJpaSpringTest extends PizzaDaoTest {
	
	@Autowired
	private void setPizzaDao(IPizzaDao pizzaDaoDataJpa) {
		this.pizzaDao = pizzaDaoDataJpa;
	}
}
