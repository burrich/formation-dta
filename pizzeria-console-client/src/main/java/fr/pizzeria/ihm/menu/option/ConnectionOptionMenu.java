package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.ihm.menu.MenuCommande;

public class ConnectionOptionMenu extends OptionMenu {

	private static final String CONNECTION_CLIENT_LIBELLE = "Se connecter";
	private Scanner sc;

	/**
	 * @param pizzaDao Interface IPizzaDao
	 * @param sc Objet Scanner
	 */
	public ConnectionOptionMenu(DaoFactory daoFactory, Scanner sc) {
		super(CONNECTION_CLIENT_LIBELLE, daoFactory);
		this.sc = sc;
	}

	@Override
	public boolean execute() {
		boolean clientTrouve = false;
		while (!clientTrouve) {
			System.out.println("Veuillez saisir votre email");
			String mail = this.sc.next();
			System.out.println("Veuillez saisir votre mot de passe");
			String psw = this.sc.next();
			
			clientTrouve = daoFactory.getClientDao().connection(mail, psw);
			if (!clientTrouve) {
				System.out.println("Email ou mot de passe incorrect");
			}
		}
		
		System.out.println("Bienvenue");
		MenuCommande menuCommande = new MenuCommande(sc, daoFactory);
		menuCommande.afficher();
		
		return false;
	}

}
