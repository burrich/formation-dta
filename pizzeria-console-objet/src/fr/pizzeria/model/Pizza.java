package fr.pizzeria.model;

/**
 * Classe Pizza
 * @author Nicolas
 */
public class Pizza {
	public static int nbPizzas = 0; // TODO: a supprimer
	
	private String code;
	private String nom;
	private double prix;
	private CategoriePizza categorie;
	
	/**
	 * Constructeur par défaut.
	 */
	public Pizza() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur avec paramètres.
	 * @param id
	 * @param code
	 * @param nom
	 * @param prix
	 */
	public Pizza(String code, String nom, double prix, CategoriePizza categorie) {
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
	}
	
	@Override
	public String toString() {
		return this.code + " -> " + this.nom + " (" + this.prix + "€) - " + this.categorie.getLibelle();
	}

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code à modifier
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom à modifier
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return prix 
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix à modifier
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return categorie
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie à modifier
	 */
	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}
}
