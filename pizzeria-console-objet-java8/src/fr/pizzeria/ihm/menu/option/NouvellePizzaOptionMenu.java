package fr.pizzeria.ihm.menu.option;

import java.util.Arrays;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
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
		Pizza newPizza = new Pizza();
		
		// TODO: InputMissmatch exception
		System.out.println(AJOUT_PIZZA_LIBELLE);
		System.out.println("Veuillez saisir le code");
		newPizza.setCode(this.sc.next());
		System.out.println("Veuillez saisir le nom (sans espace)");
		newPizza.setNom(this.sc.next());
		System.out.println("Veuillez saisir le prix");
		newPizza.setPrix(this.sc.nextDouble());
		
		boolean demandeCategorie = true;
		while (demandeCategorie) {
			System.out.println("Veuillez saisir la catégorie");
			CategoriePizza[] categoriesPizza = CategoriePizza.values();
			
			Arrays.asList(categoriesPizza).stream()
				.forEach(catPizza -> System.out.println(catPizza.ordinal() + " -> " + catPizza.getLibelle()));
			
			int saisieCategorie = this.sc.nextInt();
			if (saisieCategorie >= 0 && saisieCategorie < categoriesPizza.length) {
				newPizza.setCategorie(categoriesPizza[saisieCategorie]);
				demandeCategorie = false;
			} else {
				System.out.println("Catégorie incorrecte");
			}
		}
		
		try {
			pizzaDao.savePizza(newPizza);
		} catch (DaoException e) {
			System.err.println("Echec création de pizza");
		}
		
		return true;
	}

}
