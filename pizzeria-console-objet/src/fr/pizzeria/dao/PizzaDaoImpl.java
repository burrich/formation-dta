package fr.pizzeria.dao;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * Implémentation de l'interface IPizzaDao.
 * @see IPizzaDao
 * @author Nicolas
 */
public class PizzaDaoImpl implements IPizzaDao {
	private Pizza[] pizzas = new Pizza[100];
	
	/**
	 * Initialisation du tableau pizzas.
	 */
	public PizzaDaoImpl() {
		pizzas[0] = new Pizza(0, "PEP", "Pépéroni", 12.50);
		pizzas[1] = new Pizza(1, "MAR", "Margherita", 14.50);
		pizzas[2] = new Pizza(2, "REIN", "La Reine", 11.50);
		pizzas[3] = new Pizza(3, "FRO", "La 4 fromages", 12.00);
		pizzas[4] = new Pizza(4, "CAN", "La cannibale", 12.50);
		pizzas[5] = new Pizza(5, "SAV", "La savoyarde", 13.00);
		pizzas[6] = new Pizza(6, "ORI", "L'orientale", 13.50);
		pizzas[7] = new Pizza(7, "IND", "L'indienne", 14.00);
		Pizza.nbPizzas = 8;
	}
	
	@Override
	public Pizza[] findAllPizzas() {
		Pizza[] resultat = new Pizza[100];
		System.arraycopy(pizzas, 0, resultat, 0, resultat.length);
		
		return resultat;
	}

	@Override
	public boolean savePizza(Pizza newPizza) throws DaoException {
		boolean placeTrouve = false;
		int index = 0;
		
		while (!placeTrouve && index < pizzas.length) {
			placeTrouve = pizzas[index] == null;
			if(!placeTrouve) {
				index++;
			}
		}

		if (placeTrouve) {
			pizzas[index] = newPizza;
			Pizza.nbPizzas++;
		} else {
			throw new SavePizzaException();
		}
		
		return placeTrouve;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		ResultatRecherche resultatRecherche = rechercherPizza(codePizza);
		if (resultatRecherche.pizzaTrouve) {
			pizzas[resultatRecherche.indexPizzaTrouve] = updatePizza;
		} else {
			throw new UpdatePizzaException();
		}
		
		return resultatRecherche.pizzaTrouve;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DaoException {
		ResultatRecherche resultatRecherche = rechercherPizza(codePizza);
		if (resultatRecherche.pizzaTrouve) {
			pizzas[resultatRecherche.indexPizzaTrouve] = null;
			Pizza.nbPizzas--;
		} else {
			throw new DeletePizzaException();
		}
		return resultatRecherche.pizzaTrouve;
	}
	
	/**
	 * Recherche une pizza dans le tableau Pizza à partir de son code. 
	 * Retourne un objet ResultatRecherche.
	 * @see ResultatRecherche
	 * @param pizzas Tableau des Pizza
	 * @param codePizza Code la pizza
	 * @return objet ResultatRecherche
	 */
	private ResultatRecherche rechercherPizza(String codePizza) {
		boolean pizzaTrouve = false;
		int indexPizzaTrouve = 0;
		
		while (!pizzaTrouve && indexPizzaTrouve < pizzas.length) {
			if(pizzas[indexPizzaTrouve] != null) {
				pizzaTrouve = codePizza.equals(pizzas[indexPizzaTrouve].getCode());
			}
			if (!pizzaTrouve) {
				indexPizzaTrouve++;
			}
		}
		
		ResultatRecherche resultat = new ResultatRecherche();
		resultat.pizzaTrouve = pizzaTrouve;
		resultat.indexPizzaTrouve = indexPizzaTrouve;
		
		return resultat;
	}
	
	/**
	 * Représente le résultat d'une recherche : si la pizza a été trouvée et son index.
	 * @author Nicolas
	 */
	private static class ResultatRecherche {
		boolean pizzaTrouve;
		int indexPizzaTrouve;
	}
}
