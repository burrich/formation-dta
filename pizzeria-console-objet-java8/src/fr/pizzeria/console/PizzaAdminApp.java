package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.ihm.menu.Menu;

/**
 * Classe principale contenant le main.
 * @author Nicolas
 */
public class PizzaAdminApp {
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		IPizzaDao dao = new PizzaDaoImpl();
		Menu menu = new Menu(sc, dao);
		menu.afficher();
		sc.close();
	}
}
