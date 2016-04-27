package fr.pizzeria.console;

import java.util.Scanner;
import fr.pizzeria.model.Pizza;

/**
 * Administration d'une pizzeria en mode console
 * @author Nicolas
 */
public class PizzeriaAdminConsoleApp {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		Pizza[] pizzas = genererPizzas();
		
		int choix = 0;
		while (choix != 99) {
			afficherMenu();
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			
			switch(choix) {
			case 1:
				System.out.println("Liste des pizzas");
				afficherPizzas(pizzas);
				System.out.println("------- " + Pizza.nbPizzas + " pizzas créées depuis l’initialisation du programme");
				System.out.print("\n");
				break;
			case 2:
				System.out.println("Ajout d'une nouvelle pizza");
				pizzas[Pizza.nbPizzas] = saisiePizza(sc);
				pizzas[Pizza.nbPizzas].setId(Pizza.nbPizzas);
				Pizza.nbPizzas++;
				
				System.out.print("\n");
				break;
			case 3:
				System.out.println("Mise à jour d'une pizza");
				afficherPizzas(pizzas);
				System.out.println("Veuillez choisir la pizza à modifier");
				System.out.println("(99 pour abandonner)");
				
				int choixPizza = sc.nextInt();
				if (choixPizza != 99) {
					pizzas[choixPizza] = saisiePizza(sc);
				}
		
				System.out.print("\n");
				break;
			case 4:
				System.out.println("Suppression d'une pizza");
				afficherPizzas(pizzas);
				System.out.println("Veuillez choisir la pizza à suprrimer");
				System.out.println("(99 pour abandonner)");
				supprimerPizza(sc, pizzas);
				
				System.out.print("\n");
				break;
			default:
				break;
			}
		}	
	}
	
	/**
	 * Génere un tableau d'objets Pizza
	 * @return Tableau d'objets Pizza
	 */
	private static Pizza[] genererPizzas() {
		Pizza[] pizzas = new Pizza[100];
		pizzas[0] = new Pizza(0, "PEP", "Pépéroni", 12.50);
		pizzas[1] = new Pizza(1, "MAR", "Margherita", 14.50);;
		pizzas[2] = new Pizza(2, "REIN", "La Reine", 11.50);;
		pizzas[3] = new Pizza(3, "FRO", "La 4 fromages", 12.00);;
		pizzas[4] = new Pizza(4, "CAN", "La cannibale", 12.50);;
		pizzas[5] = new Pizza(5, "SAV", "La savoyarde", 13.00);;
		pizzas[6] = new Pizza(6, "ORI", "L'orientale", 13.50);;
		pizzas[7] = new Pizza(7, "IND", "L'indienne", 14.00);;
		Pizza.nbPizzas = 8;
		
		return pizzas;
	}
	
	/**
	 * Affiche le menu de la pizzeria.
	 */
	private static void afficherMenu() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}
	
	/**
	 * Affiche les pizzas.
	 * @param pizzas Tableau des Pizza
	 */
	private static void afficherPizzas(Pizza[] pizzas) {
		for (int i = 0; i < pizzas.length; i++) {
			if (pizzas[i] != null) {
				System.out.print(pizzas[i].getCode() + " -> ");
				System.out.print(pizzas[i].getNom() + " (");
				System.out.println(pizzas[i].getPrix() + "€)");
			}
		}
	}
	
	/**
	 * Saisie d'une pizza (pour un ajout ou une modification).
	 * @param sc Objet Scanner
	 * @return Objet Pizza
	 */
	private static Pizza saisiePizza(Scanner sc) {
		System.out.println("Veuillez saisir le code");
		String code = sc.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = sc.next();
		System.out.println("Veuillez saisir le prix");
		double prix = sc.nextDouble();
		
		Pizza pizza = new Pizza();
		pizza.setCode(code);
		pizza.setNom(nom);
		pizza.setPrix(prix);
		
		return pizza;
	}
	
	/**
	 * Supprime une pizza et trie le tableau de Pizza.
	 * @param sc Objet Scanner
	 * @param pizzas Tableau de Pizza
	 */
	private static void supprimerPizza(Scanner sc, Pizza[] pizzas) {
		int choixPizza = sc.nextInt();
		if (choixPizza != 99) {
			int numPizza = choixPizza +1;
			while (pizzas[numPizza] != null) {
				pizzas[numPizza -1] = pizzas[numPizza];
				numPizza++;
			}
			pizzas[Pizza.nbPizzas-1] = null;
		}
		Pizza.nbPizzas--;
	}
}
