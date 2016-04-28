package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.IPizzaDao;

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
	
	public abstract boolean execute();

	/**
	 * @return libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle à modifier
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
