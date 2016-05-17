package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.ihm.menu.option.ConnectionOptionMenu;
import fr.pizzeria.ihm.menu.option.InscriptionOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.QuitterOptionMenu;

/**
 * Représente le menu console.
 * @author Nicolas
 */
public class Menu {
	private static final String MENU_TITRE_LIBELLE = "Pizzeria Client";
	private Map<Integer, OptionMenu> options = new TreeMap<>();
	private Scanner sc;
	
	/**
	 * Initialise les options.
	 * @param sc Objet Scanner
	 * @param pizzaDao Interface IPizzaDao
	 */
	public Menu(Scanner sc, DaoFactory daoFactory) {
		initialiserOptions(daoFactory, sc);
	}
	
	/**
	 * Initilialise le scanner et les options.
	 * @param pizzaDao Interface IPizzaDao
	 * @param sc Objet Scanner
	 */
	private void initialiserOptions(DaoFactory daoFactory,  Scanner sc) {
		this.sc = sc;
		options.put(1, new InscriptionOptionMenu(daoFactory, this.sc));
		options.put(2, new ConnectionOptionMenu(daoFactory, this.sc));
		options.put(99, new QuitterOptionMenu());
	}

	/**
	 * Affiche le menu et saisie de l'option.
	 */
	public void afficher() {
		boolean continuer = true;
		
		while (continuer) {
			System.out.println("***** " + MENU_TITRE_LIBELLE + " *****");
			
			options.entrySet().stream()
				.forEach(optionMenuEntry -> System.out.println(optionMenuEntry.getKey() + ". " + optionMenuEntry.getValue().getLibelle()));
			
			int saisie = sc.nextInt();
			continuer = options.get(saisie).execute();
		}
	}
}
