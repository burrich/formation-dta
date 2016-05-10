package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFichierImpl;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.ihm.menu.Menu;
import fr.pizzeria.model.Pizza;

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
		Pizza p1 = new Pizza();
		Pizza p2 = new Pizza();
		p1.equals(p2);
		
		Scanner sc = new Scanner(System.in);
		IPizzaDao dao = new PizzaDaoImpl();
		Menu menu = new Menu(sc, dao);
		menu.afficher();
		sc.close();
	}
}
