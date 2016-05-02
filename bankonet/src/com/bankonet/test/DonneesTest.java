package com.bankonet.test;

import com.bankonet.ICompteStat;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public class DonneesTest {

	public static ICompteStat[] construitEchantillonComptes() {
		ICompteStat[] tabIComptes = new Compte[5];
		tabIComptes[0] = new CompteCourant(1, "compte courant 1", 0, 1000);
		tabIComptes[1] = new CompteCourant(2, "compte courant 2", 500, 200);
		tabIComptes[2] = new CompteCourant(3, "compte courant 3", 20, 0);
		tabIComptes[3] = new CompteEpargne(4, "compte épargne 4", 1, 2.54F,20000F);
		tabIComptes[4] = new CompteEpargne(5, "compte épargne 5", 1000000,13.42F, 20000F);

		return tabIComptes;
	}

}
