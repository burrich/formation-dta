package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Instanciation de la classe abstraite OptionMenu.
 * @see OptionMenu
 * @author Nicolas
 */
public class MajPizzaOptionMenu extends OptionMenu {
	private static final String MAJ_PIZZA_LIBELLE = "Modification d'une pizza";

	private Scanner sc;
	
	/**
	 * @param pizzaDao Interface IPizzaDao
	 * @param sc Objet Scanner
	 */
	public MajPizzaOptionMenu(IPizzaDao pizzaDao, Scanner sc) {
		super(MAJ_PIZZA_LIBELLE, pizzaDao);
		this.sc = sc;
	}
	
	@Override
	public boolean execute() {
		System.out.println(MAJ_PIZZA_LIBELLE);
		System.out.println("Veuillez saisir le code");
		String code = this.sc.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = this.sc.next();
		System.out.println("Veuillez saisir le prix");
		double prix = this.sc.nextDouble();
		
		Pizza updatePizza = new Pizza();
		updatePizza.setCode(code);
		updatePizza.setNom(nom);
		updatePizza.setPrix(prix);
		
		pizzaDao.updatePizza(code, updatePizza);
		
		return true;
	}

}
