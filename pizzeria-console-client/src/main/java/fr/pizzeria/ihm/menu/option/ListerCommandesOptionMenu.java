package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;

public class ListerCommandesOptionMenu extends OptionMenu {

	private static final String LISTER_COMMANDES_LIBELLE = "Lister ses commandes";
	private Scanner sc;
	
	/**
	 * @param daoFactory
	 * @param sc
	 */
	public ListerCommandesOptionMenu(DaoFactory daoFactory, Scanner sc) {
		super(LISTER_COMMANDES_LIBELLE, daoFactory);
		this.sc = sc;
	}

	@Override
	public boolean execute() {
		
		
		return false;
	}

}
