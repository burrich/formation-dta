package fr.pizzeria.ihm.menu.option;

import java.util.Collections;
import java.util.Comparator;
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
		
		Collections.sort(pizzas, new Comparator<Pizza>() {
			@Override
			public int compare(Pizza o1, Pizza o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
		});
		
		pizzas.stream().forEach(pizza -> System.out.println(pizza));
	
		return true;
	}

}
