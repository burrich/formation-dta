package fr.pizzeria.ihm.menu.option;

import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFichierImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

/**
 * Instanciation de la classe abstraite OptionMenu.
 * @see OptionMenu
 * @author Nicolas
 */
public class ImportDonneesOptionMenu extends OptionMenu {
	private static final String IMPORT_DONNEES_LIBELLE = "(BDD) Importer les données";
	
	/**
	 * @param pizzaDao Interface IPizzaDao
	 */
	public ImportDonneesOptionMenu(IPizzaDao pizzaDao) {
		super(IMPORT_DONNEES_LIBELLE, pizzaDao);
	}
	
	@Override
	public boolean execute() {
		System.out.println(IMPORT_DONNEES_LIBELLE);
		
		try {
			PizzaDaoFichierImpl daoFichierImpl = new PizzaDaoFichierImpl();
			List<Pizza> listPizzas = daoFichierImpl.findAllPizzas();
			pizzaDao.saveAllPizzas(listPizzas, 3);
		} catch (DaoException e) {
			System.err.println(e.getMessage());
		}
		
		return true;
	}

}
