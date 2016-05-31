package fr.pizzeria.console;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.pizzeria.ihm.menu.Menu;

public class Main {
	public static void main(String[] args) {
		Logger.getLogger("org").setLevel(Level.SEVERE);
		
		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String daoConfig = bundle.getString("dao.impl");

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-config.xml", daoConfig)) {
			Menu menu = context.getBean(Menu.class);
			menu.afficher();
		}
	}
}
