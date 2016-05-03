package com.bankonet.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.bankonet.CompteNonTrouveException;

/**
 * Modelise un client de bankonet.
 *
 * <p>Un client est caracterise par :
 * <ul>
 * <li> son identifiant unique
 * <li> son nom
 * <li> son prenom
 * <li> la liste de ses comptes
 * </ul>
 *
 * @author fguibert
 */
public class Client {
	private int identifiant;
	private String nom;
	private String prenom;
	private List<Compte> comptes = new ArrayList<>();
	
	/**
	 * @param nom
	 * @param prenom
	 * @param identifiant
	 */
	public Client(int identifiant, String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
	}
	
	public Client(int identifiant, String nom, String prenom, List<Compte> cList) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.comptes = cList;
	}
	
	public String toString() {
		 return " ID  : "+this.getIdentifiant() +" - "+
		    	" Nom : "+this.getNom()+" - "+
		    	" Prénom : "+this.getPrenom();
		    		
		
	}
	
	
	public float calculerAvoirGLobal()
	{
		List<Compte> tousLesComptes = new ArrayList(this.getComptes());
		float soldeTotal = 0;
		for(Compte myC : tousLesComptes) {
			soldeTotal += myC.getSolde();
		}
		return soldeTotal;
	}
	
	public void creerCompte(Compte compte) {
		this.comptes.add(compte);
	}
	
	public void supprimerCompte(Compte compte) {
		this.comptes.remove(compte);
	}
	
	public void supprimerCompte(String numero) throws CompteNonTrouveException {
		Compte compteASupprimer = retournerCompte(numero);
		if (compteASupprimer != null) {
			comptes.remove(compteASupprimer);
		} else {
			throw new CompteNonTrouveException("Le compte " + numero + " n'existe pas !");
		}
	}
	
	public Compte retournerCompte(String numero) {
		for (Compte compte : comptes) {
			if (compte.getLibelle().equals(numero));
				return compte;
		}
		
		return null;
	}
	
	/**
	 * @param comptes the comptes to set
	 */
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	/**
	 * @return the comptes
	 */
	public List<Compte> getComptes() {
		return Collections.unmodifiableList(comptes);
	}

	public Compte getCompte(int compteId) {
	    List<Compte> compteList = this.getComptes();
	    Iterator<Compte> compteIte = compteList.iterator();
	    while (compteIte.hasNext()) {
            Compte compte = (Compte) compteIte.next();
            if (compteId == compte.getIdentifiant())
                return compte;
        }
	    return null; 
	}
		
	public int getIdentifiant() {
		return identifiant;
	}
	/**
	 * Retourne le nom du client.
	 *
	 * @return java.lang.String
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Retourne le prenom du client.
	 * 
	 * @return java.lang.String
	 */
	public String getPrenom() {
		return prenom;
	}
}
