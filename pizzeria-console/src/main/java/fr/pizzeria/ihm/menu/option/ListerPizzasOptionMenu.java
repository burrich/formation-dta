package fr.pizzeria.ihm.menu.option;

import java.util.Comparator;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
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
		
		try {
			pizzaDao.findAllPizzas()
					.stream().sorted(Comparator.comparing(Pizza::getCode))
					.forEach(System.out::println);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return true;
	}

}
