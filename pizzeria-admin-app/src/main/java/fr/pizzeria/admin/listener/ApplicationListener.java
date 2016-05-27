package fr.pizzeria.admin.listener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
	@Inject private PizzaService pizzaService;;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		initPizzas();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
	
	private void initPizzas() {
		List<Pizza> pizzas = new ArrayList<>();
		
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("MAR", "Margherita", 14.50, CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));

		pizzas.forEach(p -> {
			try {
				pizzaService.savePizza(p);
			} catch (DaoException e) {
				// TODO: LOG erreur
				System.err.println("Erreur lors de l'initialisation des pizza");
			}
		});
	}
}
