package fr.pizzeria.model;

import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Classe Pizza
 * @author Nicolas
 */
public class Pizza {
	public static int nbPizzas = 0; // TODO: a supprimer
	@ToString
	private String code;
	@ToString(uppercase=false)
	private String nom;
	@ToString
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
		return Arrays.asList(this.getClass().getDeclaredFields())
				.stream()
					.filter(field -> field.getAnnotation(ToString.class) != null)
					.map(field -> {
						String result = null;
						
						try {
							result = field.getAnnotation(ToString.class).uppercase() ? field.get(this).toString().toUpperCase() : field.get(this).toString();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						return result;
					})
					.collect(Collectors.joining(" "));
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
