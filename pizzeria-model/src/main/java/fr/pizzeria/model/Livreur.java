package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="livreur")
public class Livreur {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	private String prenom;
	
	@OneToMany(mappedBy="livreur")
	private List<Commande> commandes;
	
	
	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 */
	public Livreur(Integer id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.commandes = new ArrayList<>();
	}
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the commandes
	 */
	public List<Commande> getCommandes() {
		return commandes;
	}

	/**
	 * @param commandes the commandes to set
	 */
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
}
