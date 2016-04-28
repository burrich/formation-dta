package fr.pizzeria.ihm.menu;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.menu.option.ListerPizzasOptionMenu;
import fr.pizzeria.ihm.menu.option.MajPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.QuitterOptionMenu;
import fr.pizzeria.ihm.menu.option.SupprimerPizzaOptionMenu;

/**
 * Représente le menu console.
 * @author Nicolas
 */
public class Menu {
	private static final String MENU_TITRE_LIBELLE = "Pizzeria Administration";
	private OptionMenu[] options;
	private Scanner sc;
	
	/**
	 * Initialise les options.
	 * @param sc Objet Scanner
	 * @param pizzaDao Interface IPizzaDao
	 */
	public Menu(Scanner sc, IPizzaDao pizzaDao) {
		initialiserOptions(pizzaDao, sc);
		
	}
	
	/**
	 * Initilialise le scanner et les options.
	 * @param pizzaDao Interface IPizzaDao
	 * @param sc Objet Scanner
	 */
	private void initialiserOptions(IPizzaDao pizzaDao, Scanner sc) {
		this.sc = sc;
		options = new OptionMenu[] {
			new ListerPizzasOptionMenu(pizzaDao),
			new NouvellePizzaOptionMenu(pizzaDao, this.sc),
			new MajPizzaOptionMenu(pizzaDao, this.sc),
			new SupprimerPizzaOptionMenu(pizzaDao, this.sc),
			new QuitterOptionMenu()
		};
	}

	/**
	 * Affiche le menu et saisie de l'option.
	 */
	public void afficher() {
		boolean continuer = true;
		
		while (continuer) {
			System.out.println("***** " + MENU_TITRE_LIBELLE + " *****");
			for (int i = 0; i < options.length; i++) {
				OptionMenu opt = options[i];
				System.out.println(i + "." + opt.getLibelle());
			}
			
			int saisie = sc.nextInt();
			continuer = options[saisie].execute();
		}
	}
}
