package fr.pizzeria.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=PizzaDaoSpringTest.class)
public class PizzaDaoJDBCTemplateTest {
	
	@Autowired private PizzaDaoJDBCTemplate pizzaDaoJDBCTemplate;
	
	@Test
	public void testFindAllPizzas() throws DaoException {
		List<Pizza> pizzas = pizzaDaoJDBCTemplate.findAllPizzas();
		Assert.assertEquals(4, pizzas.size());
	}
	
//	@Test
//	public void testSavePizza() throws DaoException {
//		Pizza newPizza = new Pizza("TEST", "test", 12, CategoriePizza.VIANDE);
//		pizzaDaoJDBCTemplate.savePizza(newPizza);
//		Pizza pizza = pizzaDaoJDBCTemplate.findByCode(newPizza.getCode());
//		Assert.assertTrue();
//	}
	
//	public void testSaveAllPizzas() {
//		pizzaDaoJDBCTemplate.saveAllPizzas(listPizzas, nb)
//	}
}
