package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.menu.option.AfficherPizzaTarifEleveOptionMenu;
import fr.pizzeria.ihm.menu.option.ImportDonneesOptionMenu;
import fr.pizzeria.ihm.menu.option.ListerPizzasGroupesParCategorieOptionMenu;
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
@Component
public class Menu {
	private static final String MENU_TITRE_LIBELLE = "Pizzeria Administration";
	private Map<Integer, OptionMenu> options = new TreeMap<>();

	@Autowired
	private Scanner sc;
	
	/**
	 * Initialise les options.
	 * @param pizzaDao Interface IPizzaDao
	 */
	@Autowired
	public Menu(@Qualifier("pizzaDaoJdbcTemplate") IPizzaDao pizzaDao) {
		initialiserOptions(pizzaDao);
	}
	
	/**
	 * Initilialise le scanner et les options.
	 * @param pizzaDao Interface IPizzaDao
	 */
	private void initialiserOptions(IPizzaDao pizzaDao) {
		options.put(1, new ListerPizzasOptionMenu(pizzaDao));
		options.put(2, new NouvellePizzaOptionMenu(pizzaDao, sc));
		options.put(3, new MajPizzaOptionMenu(pizzaDao, sc));
		options.put(4, new SupprimerPizzaOptionMenu(pizzaDao, sc));
		options.put(5, new ListerPizzasGroupesParCategorieOptionMenu(pizzaDao));
		options.put(6, new AfficherPizzaTarifEleveOptionMenu(pizzaDao));
		options.put(7, new ImportDonneesOptionMenu(pizzaDao));
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
