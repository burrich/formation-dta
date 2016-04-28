package fr.pizzeria.model;

/**
 * Classe Pizza
 * @author Nicolas
 */
public class Pizza {
	public static int nbPizzas = 0;
	
	private int id;
	private String code;
	private String nom;
	private double prix;
	
	/**
	 * Constructeur par défaut.
	 */
	public Pizza() {
		
	}
	
	/**
	 * Constructeur avec paramètres.
	 * @param id
	 * @param code
	 * @param nom
	 * @param prix
	 */
	public Pizza(int id, String code, String nom, double prix) {
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}
	
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id à modifier
	 */
	public void setId(int id) {
		this.id = id;
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
}
