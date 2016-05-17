package fr.pizzeria.model;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * Classe Pizza
 * @author Nicolas
 */
@Entity
@Table(name="pizza")
@NamedQuery(name="pizza.findByCode", query="select p from Pizza p where p.code=:code")
public class Pizza {
	public static int nbPizzas = 0; // TODO: a supprimer
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ToString
	@Column(name="reference")
	private String code;
	
	@ToString(uppercase=false)
	@Column(name="libelle")
	private String nom;
	
	@ToString
	private double prix;
	
	@Column(name="url_image")
	private String urlImage;
	
	@ToString
	@Enumerated(EnumType.STRING)
	private CategoriePizza categorie;
	
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
	 * @return the id
	 */
	public int getId() {
		return id;
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
		return new HashCodeBuilder(17, 37).append(code)
				.toHashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * Now using EqualsBuilder from commons-lang3
	 */
	@Override
	public boolean equals(Object obj) {		
		if (obj == null) { 
			return false; 
		}
		   
		if (obj == this) { 
			return true; 
		}
		   
		if (obj.getClass() != getClass()) {
			return false;
		}
		
		Pizza otherPizza = (Pizza) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(code, otherPizza.code)
				.isEquals();
	}

}
