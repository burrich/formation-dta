package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {
	private static final String LISTE_PIZZAS_LIBELLE = "Liste des pizzas";

	public ListerPizzasOptionMenu(IPizzaDao pizzaDao) {
		super(LISTE_PIZZAS_LIBELLE, pizzaDao);
	}
	
	@Override
	public boolean execute() {
		System.out.println(LISTE_PIZZAS_LIBELLE);
		Pizza[] pizzas = this.pizzaDao.findAllPizzas();
		
		for (Pizza p : pizzas) {
			if (p != null) {
				System.out.println(p.getCode() + " -> " + p.getNom() + "(" + p.getPrix() + "€)");
			}
		}
	
		return true;
	}

}
