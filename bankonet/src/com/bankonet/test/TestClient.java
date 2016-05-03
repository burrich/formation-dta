package com.bankonet.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bankonet.model.BankonetException;
import com.bankonet.model.Client;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;


public class TestClient {

	 public static void main(String[] args) {
		 
		
		// Utilisation d'une collection : création de la liste des comptes courants/epargne
		List<Compte> listCompte1 = new ArrayList<>();
		List<Compte> listCompte2 = new ArrayList<>();
		List<Compte> listCompte3 = new ArrayList<>();
	
		listCompte1.add(new CompteCourant(1, "compte courant 1", 0, 1000));
		listCompte1.add(new CompteEpargne(1, "compte epargne 1", 10, 2.54F, 20000));
		
		listCompte2.add(new CompteCourant(2, "compte courant 2", 6000, 200));
		listCompte2.add(new CompteEpargne(2, "compte epargne 2", 10500, 1.67F, 30000));
		
		listCompte3.add(new CompteCourant(3, "compte courant 3", -200, 300));
	
		// creation des clients
		List<Client> listClient =  new ArrayList<>();
		listClient.add(new Client(1,"GUIBERT", "Fabien", listCompte1));
		listClient.add(new Client(2,"TOTO", "Titi", listCompte2));
		listClient.add(new Client(3,"DURAND", "Jacques", listCompte3));
		
		// Test virement
		Client client2 = listClient.get(1);
		CompteCourant client2CptC = (CompteCourant) client2.getComptes().get(0);
		CompteEpargne client2CptE = (CompteEpargne) client2.getComptes().get(1);
		client2CptC.effectuerVirement(client2CptE, 1000);
		
		 for(Client myClient : listClient) {
			 	System.out.println();
			    System.out.println(myClient.toString());
			    System.out.println("Avoir global : "+myClient.calculerAvoirGLobal()+" €");
			    for(Object myCompte : myClient.getComptes()) {
			    	System.out.println(myCompte.toString());
			    }
			    
//			    for(Object myCE : myClient.getComptesEpargne()) {
//		    		System.out.println(myCE.toString2());
//			    }
			    
		 }
	 }
	 
	 
	 
}
