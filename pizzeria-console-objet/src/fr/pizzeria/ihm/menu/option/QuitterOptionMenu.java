package fr.pizzeria.ihm.menu.option;

/**
 * Instanciation de la classe abstraite OptionMenu.
 * @see OptionMenu
 * @author Nicolas
 */
public class QuitterOptionMenu extends OptionMenu {
	private static final String QUITTER_LIBELLE = "Quitter";

	/**
	 * Constructeur par défaut.
	 */
	public QuitterOptionMenu() {
		super(QUITTER_LIBELLE);
	}
	
	@Override
	public boolean execute() {
		System.out.println(QUITTER_LIBELLE);
		return false;
	}

}
