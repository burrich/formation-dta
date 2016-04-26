package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		Object[][] pizzas = {
				{"PEP", "Pépéroni", 12.50},
				{"MAR", "Margherita", 14.00},
				{"REI", "La Reine", 11.50},
				{"FRO", "Les 4 fromages", 12.00},
				{"CAN", "La cannibale", 12.50},
				{"SAV", "La savoyarde", 13.00},
				{"ORI", "L'orientale", 13.50},
				{"IND", "L'indienne", 14.00}
		};
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
					System.out.print(pizzas[i][0] + " -> ");
					System.out.print(pizzas[i][1] + " (");
					System.out.println(pizzas[i][2] + "€)");
				}
				System.out.print("\n");
				break;
			case 2:
				System.out.println("Ajout d'une nouvelle pizza");
				break;
			case 3:
				System.out.println("Mise à jour d'une pizza");
				break;
			case 4:
				System.out.println("Suppression d'une pizza");
				break;
			default:
				break;
			}
		}	
		
		
	}

}
