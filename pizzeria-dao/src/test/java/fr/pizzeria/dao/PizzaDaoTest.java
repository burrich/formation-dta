package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class PizzaDaoTest {
	
	private static final int NB_INITIAL_PIZZA = 4;
	
	protected IPizzaDao pizzaDao;

//	@Autowired
//	private PizzaDaoJDBCTemplate pizzaDaoJDBCTemplate;

	@Test
	public void testFindAllPizzas() throws DaoException {
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertEquals(NB_INITIAL_PIZZA, pizzas.size());
	}

	 @Test
	 public void testSavePizza() throws DaoException {
		 Pizza newPizza = new Pizza("TEST", "test", 12, CategoriePizza.VIANDE);
		 pizzaDao.savePizza(newPizza);
		 
		 List<Pizza> pizzas = pizzaDao.findAllPizzas();
		 assertEquals(NB_INITIAL_PIZZA + 1, pizzas.size());
		 
		 Pizza pizzaResult = pizzaDao.findByCode(newPizza.getCode());
		 assertEquals(pizzaResult.getNom(), "test");
	 }
	 
	@Test
	public void testUpdatePizza() throws DaoException {
		String code = "FRO";
		Pizza pizzaToUpdate = pizzaDao.findByCode(code);
		pizzaToUpdate.setNom("La 4 fromages - modif");
		pizzaDao.updatePizza(code, pizzaToUpdate);

		Pizza pizzaResult = pizzaDao.findByCode(code);
		assertEquals("La 4 fromages - modif", pizzaResult.getNom());
	}

	@Test
	public void testDeletePizza() throws DaoException {
		pizzaDao.deletePizza("FRO");
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		assertEquals(NB_INITIAL_PIZZA - 1, pizzas.size());
	}
	
	@Test
    public void testSaveAllPizza() throws DaoException {
        List<Pizza> pizzas = getListePizzas();
        pizzaDao.saveAllPizzas(pizzas, 3);

        List<Pizza> pizzasBdd = pizzaDao.findAllPizzas();
        assertEquals(NB_INITIAL_PIZZA + pizzas.size(), pizzasBdd.size());
    }

    @Test
    public void testSaveAllPizzaRollback() throws DaoException {
        List<Pizza> pizzas = getListePizzasWithErrors();
        try {
            pizzaDao.saveAllPizzas(pizzas, 7);
            fail("une exception aurait dû être lancée");
        } catch (DuplicateKeyException e) {
            assertNotNull(pizzaDao.findByCode("PEP 1"));
            assertNotNull(pizzaDao.findByCode("MAR 1"));
            assertNotNull(pizzaDao.findByCode("REI 1"));
            assertNotNull(pizzaDao.findByCode("FRO 1"));
            assertNotNull(pizzaDao.findByCode("CAN 1"));
            assertNotNull(pizzaDao.findByCode("SAV 1"));
            assertNotNull(pizzaDao.findByCode("ORI 1"));

            // IND 1 et SAU 1 ne doivent pas être insérés
            assertNull(pizzaDao.findByCode("IND 1"));
            assertNull(pizzaDao.findByCode("SAU 1"));
        }
    }

    private List<Pizza> getListePizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza("PEP 1", "Pépéroni 1", 12.50, CategoriePizza.VIANDE));
        pizzas.add(new Pizza("MAR 1", "Margherita 1", 14.00, CategoriePizza.SANS_VIANDE));
        pizzas.add(new Pizza("REI 1", "La Reine 1", 11.50, CategoriePizza.VIANDE));
        pizzas.add(new Pizza("FRO 1", "La 4 fromages 1", 12.00, CategoriePizza.SANS_VIANDE));
        pizzas.add(new Pizza("CAN 1", "La cannibale 1", 12.50, CategoriePizza.VIANDE));
        pizzas.add(new Pizza("SAV 1", "La savoyarde 1", 13.00, CategoriePizza.VIANDE));
        pizzas.add(new Pizza("ORI 1", "L'orientale 1", 13.50, CategoriePizza.VIANDE));
        pizzas.add(new Pizza("IND 1", "L'indienne 1", 14.00, CategoriePizza.VIANDE));
        pizzas.add(new Pizza("SAU 1", "La Saumonéta 1", 14.00, CategoriePizza.POISSON));

        return pizzas;
    }

    private List<Pizza> getListePizzasWithErrors() {
        List<Pizza> pizzas = getListePizzas();
        pizzas.add(new Pizza("IND 1", "L'indienne 1 doublon", 14.00, CategoriePizza.VIANDE));

        return pizzas;
    }
}
