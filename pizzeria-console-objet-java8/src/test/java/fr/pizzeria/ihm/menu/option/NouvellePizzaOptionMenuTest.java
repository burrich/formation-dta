package fr.pizzeria.ihm.menu.option;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

public class NouvellePizzaOptionMenuTest {

	private NouvellePizzaOptionMenu optionMenu;
	private IPizzaDao pizzaDao;
	
	@Rule public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Rule public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Before
	public void setUp() {
		Scanner scanner = new Scanner(System.in);
		pizzaDao = new PizzaDaoImpl();
		optionMenu = new NouvellePizzaOptionMenu(pizzaDao, scanner);
	}
	
	@Test
	public void testExecute() throws DaoException, IOException {
		systemInMock.provideLines("NEW", "Newpizza", "12,0", "0");
		boolean next = optionMenu.execute();
		assertTrue(next);
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		Optional<Pizza> pizzaOpt = pizzas.stream().filter(p -> "NEW".equals(p.getCode())).findFirst();
		assertTrue(pizzaOpt.isPresent());
		Pizza pizza = pizzaOpt.get();
		assertEquals("Newpizza", pizza.getNom());
		assertTrue(12 == pizza.getPrix());
		assertEquals(CategoriePizza.VIANDE, pizza.getCategorie());
		
		String outAttendu = Files.lines(Paths.get("src/test/resources/resultatNouvellePizza.txt")).collect(Collectors.joining(System.lineSeparator()));
		outAttendu += System.lineSeparator();
		String log = systemOutRule.getLog();
		assertEquals(outAttendu, log);
	}

}
