package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		Object[][] pizzas = new Object[100][3];
		pizzas[0] = new Object[] {"PEP", "Pépéroni", 12.50};
		pizzas[1] = new Object[] {"MAR", "Margherita", 14.00};
		pizzas[2] = new Object[] {"REI", "La Reine", 11.50};
		pizzas[3] = new Object[] {"FRO", "Les 4 fromages", 12.00};
		pizzas[4] = new Object[] {"CAN", "La cannibale", 12.50};
		pizzas[5] = new Object[] {"SAV", "La savoyarde", 13.00};
		pizzas[6] = new Object[] {"ORI", "L'orientale", 13.50};
		pizzas[7] = new Object[] {"IND", "L'indienne", 14.00};
		int nbPizzas = 8;
		
		int choice = 0;
		
		while (choice != 99) {
			System.out.println("***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir");
			
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Liste des pizzas");
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i][0] != null) {
						System.out.print(pizzas[i][0] + " -> ");
						System.out.print(pizzas[i][1] + " (");
						System.out.println(pizzas[i][2] + "€)");
					}
				}
				System.out.print("\n");
				break;
			case 2:
				System.out.println("Ajout d'une nouvelle pizza");
				System.out.println("Veuillez saisir le code");
				String code = sc.next();
				System.out.println("Veuillez saisir le nom (sans espace)");
				String name = sc.next();
				System.out.println("Veuillez saisir le prix");
				double price = sc.nextDouble();
				
				pizzas[nbPizzas] = new Object[] {code, name, price};
				nbPizzas++;

				System.out.print("\n");
				break;
			case 3:
				System.out.println("Mise à jour d'une pizza");
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i][0] != null) {
						System.out.print(pizzas[i][0] + " -> ");
						System.out.print(pizzas[i][1] + " (");
						System.out.println(pizzas[i][2] + "€)");
					}
				}
				System.out.println("Veuillez choisir la pizza à modifier");
				System.out.println("(99 pour abandonner)");
				
				int pizzaChoice = sc.nextInt();
				if (pizzaChoice != 99) {
					System.out.println("Ajout d'une nouvelle pizza");
					System.out.println("Veuillez saisir le code");
					code = sc.next();
					System.out.println("Veuillez saisir le nom (sans espace)");
					name = sc.next();
					System.out.println("Veuillez saisir le prix");
					price = sc.nextDouble();
					
					pizzas[pizzaChoice] = new Object[] {code, name, price};
				}
		
				System.out.print("\n");
				break;
			case 4:
				System.out.println("Suppression d'une pizza");
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i][0] != null) {
						System.out.print(pizzas[i][0] + " -> ");
						System.out.print(pizzas[i][1] + " (");
						System.out.println(pizzas[i][2] + "€)");
					}
				}
				System.out.println("Veuillez choisir la pizza à suprrimer");
				System.out.println("(99 pour abandonner)");
				
				pizzaChoice = sc.nextInt();
				if (pizzaChoice != 99) {
					int numPizza = pizzaChoice +1;
					while (pizzas[numPizza][0] != null) {
						pizzas[numPizza -1] = pizzas[numPizza];
						numPizza++;
					}
					pizzas[nbPizzas-1] = new Object[] {null, null, null};
				}
				nbPizzas--;
		
				System.out.print("\n");
				break;
			default:
				break;
			}
		}	
		
		
	}

}
