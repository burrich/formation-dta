package fr.pizzeria.console;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.pizzeria.ihm.menu.Menu;

public class Main {
	public static void main(String[] args) {
		Logger.getLogger("org").setLevel(Level.SEVERE);
		
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PizzeriaAppSpringConfig.class)) {
			Menu menu = context.getBean(Menu.class);
			menu.afficher();
		}
	}
}
