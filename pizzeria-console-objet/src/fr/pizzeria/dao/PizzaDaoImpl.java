package fr.pizzeria.dao;

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
	}
	
	@Override
	public Pizza[] findAllPizzas() {
		Pizza[] resultat = new Pizza[100];
		System.arraycopy(pizzas, 0, resultat, 0, resultat.length);
		
		return resultat;
	}

	@Override
	public boolean savePizza(Pizza newPizza) {
		pizzas[Pizza.nbPizzas] = newPizza;
		Pizza.nbPizzas++;
		
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) {
		ResultatRecherche resultatRecherche = rechercherPizza(pizzas, codePizza);
		if (resultatRecherche.pizzaTrouve) {
			pizzas[resultatRecherche.indexPizzaTrouve].setCode(updatePizza.getCode());
			pizzas[resultatRecherche.indexPizzaTrouve].setNom(updatePizza.getNom());
			pizzas[resultatRecherche.indexPizzaTrouve].setPrix(updatePizza.getPrix());
			
			return true;
		} else {
			return false;
		}
	}


	@Override
	public boolean deletePizza(String codePizza) {
		ResultatRecherche resultatRecherche = rechercherPizza(pizzas, codePizza);
		if (resultatRecherche.pizzaTrouve) {
			pizzas[resultatRecherche.indexPizzaTrouve] = null;
			Pizza.nbPizzas--;
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Recherche une pizza dans le tableau Pizza à partir de son code. 
	 * Retourne un objet ResultatRecherche.
	 * @see ResultatRecherche
	 * @param pizzas Tableau des Pizza
	 * @param codePizza Code la pizza
	 * @return objet ResultatRecherche
	 */
	private ResultatRecherche rechercherPizza(Pizza[] pizzas, String codePizza) {
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
