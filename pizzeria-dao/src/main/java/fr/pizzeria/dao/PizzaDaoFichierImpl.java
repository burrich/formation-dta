package fr.pizzeria.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Impl�mentation de l'interface IPizzaDao.
 * @see IPizzaDao
 * @author Nicolas
 */
public class PizzaDaoFichierImpl implements IPizzaDao {
	private static final String REPERTOIRE_DATA = "data";
	
	private Map<String, Pizza> pizzas = new HashMap<>();
	
	/**
	 * Initialisation de la map pizzas.
	 */
	public PizzaDaoFichierImpl() {
		pizzas.put("PEP", new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzas.put("MAR", new Pizza("MAR", "Margherita", 14.50, CategoriePizza.SANS_VIANDE));
		pizzas.put("REIN", new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzas.put("FRO", new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzas.put("CAN", new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzas.put("SAV", new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzas.put("ORI", new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		pizzas.put("IND", new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
	}
	
	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		try (Stream<Path> dataStream = Files.list(Paths.get(REPERTOIRE_DATA))) {
			return dataStream.map(path -> {
					Pizza p = new Pizza();
					
					String code = path.getFileName().toString().replaceAll(".txt", "");
					p.setCode(code);
					
					try {
						String lignes = Files.readAllLines(path).get(0);
						String[] ligneTab = lignes.split(";");
						p.setNom(ligneTab[0]);
						p.setPrix(Double.valueOf(ligneTab[1]));
						p.setCategorie(CategoriePizza.valueOf(ligneTab[2]));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return p;
				})
				.collect(Collectors.toList());
			
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean savePizza(Pizza newPizza) throws DaoException {
		String codePizza = newPizza.getCode();
		
		if (pizzas.containsKey(codePizza)) {
			throw new SavePizzaException("code pizza déjà présent");
		}
		
		pizzas.put(codePizza, newPizza);
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		if (pizzas.containsKey(codePizza)) {
			pizzas.put(codePizza, updatePizza);
		} else {
			throw new UpdatePizzaException("code pizza non trouvé");
		}
		
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DaoException {
		if (pizzas.containsKey(codePizza)) {
			pizzas.remove(codePizza);
		} else {
			throw new DeletePizzaException("code pizza non trouvé");
		}
		
		return true;
	}

	@Override
	public boolean saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}
}
