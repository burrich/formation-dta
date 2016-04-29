package fr.pizzeria.ihm.menu.option;

import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Instanciation de la classe abstraite OptionMenu.
 * @see OptionMenu
 * @author Nicolas
 */
public class ListerPizzasOptionMenu extends OptionMenu {
	private static final String LISTE_PIZZAS_LIBELLE = "Liste des pizzas";

	/**
	 * @param pizzaDao Interface IPizzaDao
	 */
	public ListerPizzasOptionMenu(IPizzaDao pizzaDao) {
		super(LISTE_PIZZAS_LIBELLE, pizzaDao);
	}
	
	@Override
	public boolean execute() {
		System.out.println(LISTE_PIZZAS_LIBELLE);
		List<Pizza> pizzas = this.pizzaDao.findAllPizzas();
		
		for (Pizza p : pizzas) {
			System.out.println(p.getCode() + " -> " + p.getNom() + "(" + p.getPrix() + "€)");
		}
	
		return true;
	}

}
