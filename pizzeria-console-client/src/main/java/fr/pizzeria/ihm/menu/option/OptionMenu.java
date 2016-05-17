package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.DaoFactory;

/**
 * Classe mère abstraite des options menu.
 * @author Nicolas
 */
public abstract class OptionMenu {
	private String libelle;
	protected DaoFactory daoFactory;
	
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
	public OptionMenu(String libelle, DaoFactory daoFactory) {
		this(libelle);
		this.daoFactory = daoFactory;
	}
	
	/**
	 * Execute l'action de l'option.
	 * @return Résultat de l'exécution
	 */
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
