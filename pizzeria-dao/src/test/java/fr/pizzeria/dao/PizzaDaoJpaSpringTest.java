package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = SpringDataJpaConfig.class)
public class PizzaDaoJpaSpringTest extends PizzaDaoTest {
	
	@Autowired
	private void setPizzaDao(@Qualifier("pizzaDaoDataJpaSpring") IPizzaDao pizzaDaoDataJpaSpring) {
		this.pizzaDao = pizzaDaoDataJpaSpring;
	}
}
