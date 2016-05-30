package fr.pizzeria.console;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFichierImpl;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.dao.PizzaDaoJDBCImpl;
import fr.pizzeria.dao.PizzaDaoJpa;
import fr.pizzeria.dao.PizzaDaoREST;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.ihm.menu.Menu;

/**
 * Classe principale contenant le main.
 * @author Nicolas
 */
public class PizzaAdminApp {
	/**
	 * Main class
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String confString = bundle.getString("dao.impl");
		Integer daoImplConf = Integer.valueOf(confString);
				
		switch (daoImplConf) {
		case 0:
			lancerApplication(new PizzaDaoImpl());
			break;
		case 1:
			lancerApplication(new PizzaDaoFichierImpl());
			break;
		case 2:
			ResourceBundle jdbcBundle = ResourceBundle.getBundle("jdbc");
			String driver = jdbcBundle.getString("jdbc.driver");
			String url = jdbcBundle.getString("jdbc.url");
			String user = jdbcBundle.getString("jdbc.user");
			String password = jdbcBundle.getString("jdbc.password");
			
			try {
				lancerApplication(new PizzaDaoJDBCImpl(driver, url, user, password));
			} catch (DaoException e) {
				System.err.println("Echec lors de l'instanciation de la classe PizzaDaoJDBCImpl");
			}
			break;
		case 3:
			java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
			EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("pizzeria-console");
			lancerApplication(new PizzaDaoJpa(emFactory));;
			emFactory.close();
		case 4:
			// REST impl
			lancerApplication(new PizzaDaoREST());
			break;
		default:
			System.err.println("Aucune configuration Dao trouvée. Le fichier application.properties est-il correctement configuré ?");
			break;
		}
		
	}
	
	private static void lancerApplication(IPizzaDao dao) {
		try(Scanner sc = new Scanner(System.in)) {
			Menu menu = new Menu(sc,dao);
			menu.afficher();
		}
	}
}
