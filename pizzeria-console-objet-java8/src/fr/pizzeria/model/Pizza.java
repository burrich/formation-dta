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
	 * Constructeur par d�faut.
	 */
	public Pizza() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructeur avec param�tres.
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

	/**
	 * @return categorie
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie � modifier
	 */
	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
