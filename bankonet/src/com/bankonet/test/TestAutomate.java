package com.bankonet.test;

import com.bankonet.ICompteStat;

public class TestAutomate {

	public static void main(String[] args) {
		ICompteStat[] comptes = DonneesTest.construitEchantillonComptes();
		float moySoldes = 0.0F;
		
		for (ICompteStat iCompteStat : comptes) {
			moySoldes += iCompteStat.getSolde();
		}
		
		moySoldes /= comptes.length;
		System.out.println("Moyenne des soldes des comptes : " + moySoldes);
	}

}
