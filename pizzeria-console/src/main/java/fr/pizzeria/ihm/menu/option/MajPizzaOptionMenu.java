package fr.pizzeria.ihm.menu.option;

import java.util.Arrays;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
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
		Pizza updatePizza = new Pizza();
		
		System.out.println(MAJ_PIZZA_LIBELLE);
		System.out.println("Veuillez saisir le code");
		String code = this.sc.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = this.sc.next();
		System.out.println("Veuillez saisir le prix");
		double prix = this.sc.nextDouble();
		
		boolean demandeCategorie = true;
		while (demandeCategorie) {
			System.out.println("Veuillez saisir la catégorie");
			CategoriePizza[] categoriesPizza = CategoriePizza.values();
			
			Arrays.asList(categoriesPizza).stream()
				.forEach(catPizza -> System.out.println(catPizza.ordinal() + " -> " + catPizza.getLibelle()));
			
			int saisieCategorie = this.sc.nextInt();
			if (saisieCategorie >= 0 && saisieCategorie < categoriesPizza.length) {
				updatePizza.setCategorie(categoriesPizza[saisieCategorie]);
				demandeCategorie = false;
			} else {
				System.out.println("Catégorie incorrecte");
			}
		}
		
		updatePizza.setCode(code);
		updatePizza.setNom(nom);
		updatePizza.setPrix(prix);
		
		try {
			pizzaDao.updatePizza(code, updatePizza);
		} catch (DaoException e) {
			System.err.println("Echec mise à jour pizza");
		}
		
		return true;
	}

}
