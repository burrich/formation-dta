package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Instanciation de la classe abstraite OptionMenu.
 * @see OptionMenu
 * @author Nicolas
 */
public class NouvellePizzaOptionMenu extends OptionMenu {
	private static final String AJOUT_PIZZA_LIBELLE = "Ajout d'une nouvelle pizza";

	private Scanner sc;
	
	/**
	 * @param pizzaDao Interface IPizzaDao
	 * @param sc Objet Scanner
	 */
	public NouvellePizzaOptionMenu(IPizzaDao pizzaDao, Scanner sc) {
		super(AJOUT_PIZZA_LIBELLE, pizzaDao);
		this.sc = sc;
	}
	
	@Override
	public boolean execute() {
		System.out.println(AJOUT_PIZZA_LIBELLE);
		System.out.println("Veuillez saisir le code");
		String code = this.sc.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = this.sc.next();
		System.out.println("Veuillez saisir le prix");
		double prix = this.sc.nextDouble();
		
		Pizza newPizza = new Pizza();
		newPizza.setCode(code);
		newPizza.setNom(nom);
		newPizza.setPrix(prix);
		
		pizzaDao.savePizza(newPizza);
		
		return true;
	}

}
