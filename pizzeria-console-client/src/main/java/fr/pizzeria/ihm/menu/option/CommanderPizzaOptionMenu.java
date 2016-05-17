package fr.pizzeria.ihm.menu.option;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.StatutCommande;

public class CommanderPizzaOptionMenu extends OptionMenu {

	private static final String COMMANDE_PIZZA_LIBELLE = "Commander une pizza";
	private Scanner sc;
	
	/**
	 * @param daoFactory
	 * @param sc
	 */
	public CommanderPizzaOptionMenu(DaoFactory daoFactory, Scanner sc) {
		super(COMMANDE_PIZZA_LIBELLE, daoFactory);
		this.sc = sc;
	}

	@Override
	public boolean execute() {
		try {
			List<Pizza> pizzas = daoFactory.getPizzaDao().findAllPizzas();
			pizzas.forEach(System.out::println);
		} catch (DaoException e) {
			System.err.println("Impossible de récupérer les pizzas");
		}
		
		List<Pizza> pizzasCommandees = new ArrayList<>();
		boolean continuer = true;
		while (continuer) {
			System.out.println("Choix : (99 pour quitter)");
			String codeSaisie = sc.next();
			
			if (codeSaisie.equals("99")) {
				continuer = false;
			} else {
				try {
					Pizza pizza = daoFactory.getPizzaDao().findByCode(codeSaisie);
					pizzasCommandees.add(pizza);
				} catch (DaoException e) {
					System.err.println("Impossible de récupérer la pizza");
				}
			}
		}
		
		if (pizzasCommandees.size() > 0) {
			Commande commande = new Commande(null, null, StatutCommande.EN_ATTENTE, null, null, null);
//			commande.setNumeroCommande("COMMANDE_"); //+ commande.getId());
			daoFactory.getCommandeDao().saveCommande(commande);
		}
		
		return true;
	}

}
