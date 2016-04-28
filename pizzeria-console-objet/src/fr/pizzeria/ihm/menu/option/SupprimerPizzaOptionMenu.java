package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;

/**
 * Instanciation de la classe abstraite OptionMenu.
 * @see OptionMenu
 * @author Nicolas
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {
	private static final String SUPPRIMER_PIZZA_LIBELLE = "Suppression pizza";

	private Scanner sc;
	
	/**
	 * @param pizzaDao Interface IPizzaDao
	 * @param sc Objet Scanner
	 */
	public SupprimerPizzaOptionMenu(IPizzaDao pizzaDao, Scanner sc) {
		super(SUPPRIMER_PIZZA_LIBELLE, pizzaDao);
		this.sc = sc;
	}
	
	@Override
	public boolean execute() {
		System.out.println(SUPPRIMER_PIZZA_LIBELLE);
		System.out.println("Veuillez saisir le code");
		String codePizza = sc.next();
		pizzaDao.deletePizza(codePizza);
		
		return true;
	}

}
