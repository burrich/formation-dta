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
	 * Constructeur par d�faut.
	 */
	public Pizza() {
		
	}
	
	/**
	 * Constructeur avec param�tres.
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
	 * @param id � modifier
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
	 * @param code � modifier
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
	 * @param nom � modifier
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
	 * @param prix � modifier
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}
}
