package com.bankonet.model;

import com.bankonet.CreditException;
import com.bankonet.DebitException;

/**
 * @author fguibert
 */
public final class CompteEpargne extends Compte {
    private float tauxInteret;
    private static int nombreComptesEpargnes = 0;

    private float plafond;

    /**
     * @param id
     * @param libelle
     * @param solde
     * @param tauxInteret
     * @param plafond
     */
    public CompteEpargne(int id, String libelle, float solde,
            float tauxInteret, float plafond) {
        super(id, libelle, solde);
        this.tauxInteret = tauxInteret;
        this.plafond = plafond;
        nombreComptesEpargnes++;
    }

    /**
     * Le montant ne doit pas etre superieur au plafond de credit autorise
     * en une fois
     */
    public boolean creditAutorise(float montant) throws CreditException {
        if (montant+getSolde() < getPlafond()) {
            return true;
        } else {
            throw new CreditException("Le compte epargne "+ this.getIdentifiant() + " a pour plafond de credit : " + this.getPlafond());
        }
    }

    public boolean debitAutorise(float montant) throws DebitException {
        if (getSolde() - montant >= 0) {
            return true;
        } else {
            throw new DebitException("Montant trop eleve : le solde du compte epargne "+ this.getIdentifiant() + " ne peut etre negatif" );
        }
    }
    
    @Override
    public void debiter(float montant) {
    	try {
			if (debitAutorise(montant)) {
				super.debiter(montant);
			}
		} catch (DebitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    public void crediter(float montant) {
    	try {
			if (creditAutorise(montant)) {
				super.crediter(montant);
			}
		} catch (CreditException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    public String toString() {
        return  super.toString()+
        		" - Taux interet : "+this.getTauxInteret()+" % "+
	    		" - Plafond : "+this.getPlafond();
    }

    /**
     * Accesseur de la propriete <code>plafond</code>.
     * 
     * @return float
     */
    public float getPlafond() {
        return plafond;
    }

    /**
     * Mutateur de la propriete <code>plafond</code>.
     * 
     * @param newPlafond
     *            Nouveau plafond
     */
    public void setPlafond(float newPlafond) {
        plafond = newPlafond;
    }

    /**
     * Accesseur de la propriete <code>tauxInterêt</code>.
     * 
     * @return float
     */
    public float getTauxInteret() {
        return tauxInteret;
    }

    /**
     * Mutateur de la propriete <code>tauxInteret</code>.
     * 
     * @param newTauxInteret
     *            Nouveau taux d'interet
     */
    public void setTauxInteret(float newTauxInteret) {
        tauxInteret = newTauxInteret;
    }
}