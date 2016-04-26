package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		String[][] pizzas = {
				{"0", "PEP", "Pépéroni", "12.50"},
				{"1", "MAR", "Margherita", "14.00"},
				{"2", "REI", "La Reine", "11.50"},
				{"3", "FRO", "Les 4 fromages", "12.00"},
				{"4", "CAN", "La cannibale", "12.50"},
				{"5", "SAV", "La savoyarde", "13.00"},
				{"6", "ORI", "L'orientale", "13.50"},
				{"7", "IND", "L'indienne", "14.00"}
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
					System.out.print(pizzas[i][1] + " -> ");
					System.out.print(pizzas[i][2] + " (");
					System.out.println(pizzas[i][3] + "€)");
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
