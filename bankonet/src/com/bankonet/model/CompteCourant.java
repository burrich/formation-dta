package com.bankonet.model;

import com.bankonet.CreditException;
import com.bankonet.DebitException;

/**
 * @author fguibert
 */
public final class CompteCourant extends Compte {
    private float decouvertAutorise;
    public static int nombreComptesCourants = 0;
 
    public CompteCourant(int id, String libelle, float solde, float decouvertAutorise) {

        super(id, libelle, solde);

        this.decouvertAutorise = decouvertAutorise;
        nombreComptesCourants++;
    }

    /**
     * Le credit d'un compte courant est toujours autorise
     */
    public boolean creditAutorise(float montant) throws CreditException {
    	//super.crediter(montant);
        return true;
    }

    public boolean debitAutorise(float montant) throws DebitException {
        if (this.getSolde() + this.getDecouvertAutorise() >= montant) {
        	//debiter(montant);
            return true;
        } else {
            throw new DebitException("Montant trop eleve : le solde du compte courant "+ this.getIdentifiant() + " ne peut descendre en dessous du decouvert autorise" );
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
    
    
    /**
     * Accesseur de la propriete <code>decouvertAutorise</code>.
     * 
     * @return float
     */
    public float getDecouvertAutorise() {
        return decouvertAutorise;
    }
    
    public String toString() {
        return   super.toString()+
	    		" - Découvert autorisé : "+this.getDecouvertAutorise()+" €";
    }
}