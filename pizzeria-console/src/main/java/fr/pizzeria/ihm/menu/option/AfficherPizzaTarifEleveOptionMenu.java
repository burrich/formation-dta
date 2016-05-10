package fr.pizzeria.ihm.menu.option;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

/**
 * Instanciation de la classe abstraite OptionMenu.
 * @see OptionMenu
 * @author Nicolas
 */
public class AfficherPizzaTarifEleveOptionMenu extends OptionMenu {
	private static final String LISTE_PIZZAS_LIBELLE = "Affichage de la pizza au tarif le plus élevé";

	/**
	 * @param pizzaDao Interface IPizzaDao
	 */
	public AfficherPizzaTarifEleveOptionMenu(IPizzaDao pizzaDao) {
		super(LISTE_PIZZAS_LIBELLE, pizzaDao);
	}
	
	@Override
	public boolean execute() {
		System.out.println(LISTE_PIZZAS_LIBELLE);
		
		List<Pizza> pizzas;
		try {
			pizzas = this.pizzaDao.findAllPizzas();
			
			pizzas.stream()
			.max(Comparator.comparing(Pizza::getPrix))
			.ifPresent(System.out::println);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
