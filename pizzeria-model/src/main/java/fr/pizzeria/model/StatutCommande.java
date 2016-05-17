package fr.pizzeria.model;

public enum StatutCommande {
	LIVREE("Livrée"), EN_ATTENTE("En attente");
	
	private String libelle;
	
	private StatutCommande(String statut) {
		this.libelle = statut;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}
}
