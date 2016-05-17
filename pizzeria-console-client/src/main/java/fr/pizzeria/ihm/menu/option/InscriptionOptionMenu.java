package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Client;

public class InscriptionOptionMenu extends OptionMenu {

	private static final String INSCRIPTION_CLIENT_LIBELLE = "S'inscrire";
	private Scanner sc;
	
	/**
	 * @param pizzaDao Interface IPizzaDao
	 * @param sc Objet Scanner
	 */
	public InscriptionOptionMenu(DaoFactory daoFactory, Scanner sc) {
		super(INSCRIPTION_CLIENT_LIBELLE, daoFactory);
		this.sc = sc;
	}
	
	@Override
	public boolean execute() {
		System.out.println(INSCRIPTION_CLIENT_LIBELLE);
		System.out.println("Veuillez saisir votre nom");
		String nom = this.sc.next();
		System.out.println("Veuillez saisir votre prénom");
		String prenom = this.sc.next();
		System.out.println("Veuillez saisir votre email");
		String mail = this.sc.next();
		System.out.println("Veuillez saisir votre mot de passe");
		String psw = this.sc.next();
//		String pswDigest = DigestUtils.sha512Hex(psw);
		
		Client newClient = new Client(nom, prenom, mail, psw);
		
		try {
			daoFactory.getClientDao().saveClient(newClient);
			System.out.println("Client créé");
		} catch (DaoException e) {
			System.err.println("Erreur lors de l'enregistrement du client");
		}
		
		return true;
	}

}
