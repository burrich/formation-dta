package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		Pizza pep = new Pizza();
		pep.id = 0;
		pep.code = "PEP";
		pep.nom = "Pépéroni";
		pep.prix = 12.50;
		
		Pizza mar = new Pizza();
		mar.id = 1;
		mar.code = "MAR";
		mar.nom = "Margherita";
		mar.prix = 14.00;
		
		Pizza rein = new Pizza();
		rein.id = 2;
		rein.code = "REIN";
		rein.nom = "La Reine";
		rein.prix = 11.50;
		
		Pizza fro = new Pizza();
		fro.id = 3;
		fro.code = "FRO";
		fro.nom = "La 4 fromages";
		fro.prix = 12.00;
		
		Pizza can = new Pizza();
		can.id = 4;
		can.code = "CAN";
		can.nom = "La cannibale";
		can.prix = 12.50;
		
		Pizza sav = new Pizza();
		sav.id = 5;
		sav.code = "SAV";
		sav.nom = "La savoyarde";
		sav.prix = 13.00;
		
		Pizza ori = new Pizza();
		ori.id = 6;
		ori.code = "ORI";
		ori.nom = "L'orientale";
		ori.prix = 13.50;
		
		Pizza ind = new Pizza();
		ind.id = 7;
		ind.code = "IND";
		ind.nom = "L'indienne";
		ind.prix = 14.00;
		
		Pizza[] pizzas = new Pizza[100];
		pizzas[0] = pep;
		pizzas[1] = mar;
		pizzas[2] = rein;
		pizzas[3] = fro;
		pizzas[4] = can;
		pizzas[5] = sav;
		pizzas[6] = ori;
		pizzas[7] = ind;
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
					if (pizzas[i] != null) {
						System.out.print(pizzas[i].code + " -> ");
						System.out.print(pizzas[i].nom + " (");
						System.out.println(pizzas[i].prix + "€)");
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
				
				pizzas[nbPizzas] = new Pizza();
				pizzas[nbPizzas].id = nbPizzas;
				pizzas[nbPizzas].code = code;
				pizzas[nbPizzas].nom = name;
				pizzas[nbPizzas].prix = price;
				nbPizzas++;

				System.out.print("\n");
				break;
			case 3:
				System.out.println("Mise à jour d'une pizza");
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i] != null) {
						System.out.print(pizzas[i].code + " -> ");
						System.out.print(pizzas[i].nom + " (");
						System.out.println(pizzas[i].prix + "€)");
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
					
					pizzas[pizzaChoice].code = code;
					pizzas[pizzaChoice].nom = name;
					pizzas[pizzaChoice].prix = price;
				}
		
				System.out.print("\n");
				break;
			case 4:
				System.out.println("Suppression d'une pizza");
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i] != null) {
						System.out.print(pizzas[i].code + " -> ");
						System.out.print(pizzas[i].nom + " (");
						System.out.println(pizzas[i].prix + "€)");
					}
				}
				System.out.println("Veuillez choisir la pizza à suprrimer");
				System.out.println("(99 pour abandonner)");
				
				pizzaChoice = sc.nextInt();
				if (pizzaChoice != 99) {
					int numPizza = pizzaChoice +1;
					while (pizzas[numPizza] != null) {
						pizzas[numPizza -1] = pizzas[numPizza];
						numPizza++;
					}
					pizzas[nbPizzas-1] = null;
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
