package fr.pizzeria.model;

import java.lang.reflect.Field;


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
		String chaineRetour = "";
		
		for (Field f : Pizza.class.getDeclaredFields()) {
			try {
				ToString annotation = f.getAnnotation(ToString.class);
				
				if (annotation != null) {
					boolean uppercase = annotation.uppercase();
					chaineRetour += uppercase ? f.get(this).toString().toUpperCase() : f.get(this).toString();
					chaineRetour += " ";
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return chaineRetour;
		//return this.code + " -> " + this.nom + " (" + this.prix + "€) - " + this.categorie.getLibelle();
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
