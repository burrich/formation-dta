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
public class ListerPizzasGroupesParCategorieOptionMenu extends OptionMenu {
	private static final String LISTE_PIZZAS_LIBELLE = "Liste des pizzas par catégorie";

	/**
	 * @param pizzaDao Interface IPizzaDao
	 */
	public ListerPizzasGroupesParCategorieOptionMenu(IPizzaDao pizzaDao) {
		super(LISTE_PIZZAS_LIBELLE, pizzaDao);
	}
	
	@Override
	public boolean execute() {
		System.out.println(LISTE_PIZZAS_LIBELLE);
		List<Pizza> pizzas;
		
		try {
			pizzas = this.pizzaDao.findAllPizzas();
			
			pizzas.stream()
			.collect(Collectors.groupingBy(Pizza::getCategorie))
			.forEach((categorie, listePizzas) -> {
				System.out.println(categorie.getLibelle());
				listePizzas.stream()
					.sorted(Comparator.comparing(Pizza::getCode))
					.forEach(System.out::println);
			});
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		return true;
	}

}
