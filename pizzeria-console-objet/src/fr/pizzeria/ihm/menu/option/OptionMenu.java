package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.IPizzaDao;

/**
 * Classe m�re abstraite des options menu.
 * @author Nicolas
 */
public abstract class OptionMenu {
	private String libelle;
	protected IPizzaDao pizzaDao;
	
	/**
	 * @param libelle
	 */
	public OptionMenu(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * @param libelle
	 * @param pizzaDao
	 */
	public OptionMenu(String libelle, IPizzaDao pizzaDao) {
		this(libelle);
		this.pizzaDao = pizzaDao;
	}
	
	/**
	 * Execute l'action de l'option.
	 * @return R�sultat de l'ex�cution
	 */
	public abstract boolean execute();

	/**
	 * @return libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle � modifier
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
