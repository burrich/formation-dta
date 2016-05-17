package fr.pizzeria.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="commande")
public class Commande {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="numero_commande")
	private String numeroCommande;
	
	@Enumerated(EnumType.STRING)
	private StatutCommande status;
	
	@Column(name="date_commande")
	private LocalDateTime dateCommande;
	
	@ManyToOne
	@JoinColumn(name="livreur_id")
	private Livreur livreur;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;

	@ManyToMany
	@JoinTable(name="commande_pizza", 
		joinColumns=@JoinColumn(name="commande_id", referencedColumnName="id"), 
		inverseJoinColumns=@JoinColumn(name="pizza_id", referencedColumnName="id"))
	private List<Pizza> pizzas;
	
	
	/**
	 * @param id
	 * @param numeroCommande
	 * @param status
	 * @param dateCommande
	 * @param livreur
	 * @param client
	 */
	public Commande(Integer id, String numeroCommande, StatutCommande status, LocalDateTime dateCommande, Livreur livreur,
			Client client) {
		this.id = id;
		this.numeroCommande = numeroCommande;
		this.status = status;
		this.dateCommande = dateCommande;
		this.livreur = livreur;
		this.client = client;
		this.pizzas = new ArrayList<>();
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
	 * @return the numeroCommande
	 */
	public String getNumeroCommande() {
		return numeroCommande;
	}

	/**
	 * @param numeroCommande the numeroCommande to set
	 */
	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	/**
	 * @return the status
	 */
	public StatutCommande getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatutCommande status) {
		this.status = status;
	}

	/**
	 * @return the dateCommande
	 */
	public LocalDateTime getDateCommande() {
		return dateCommande;
	}

	/**
	 * @param dateCommande the dateCommande to set
	 */
	public void setDateCommande(LocalDateTime dateCommande) {
		this.dateCommande = dateCommande;
	}

	/**
	 * @return the livreur
	 */
	public Livreur getLivreur() {
		return livreur;
	}

	/**
	 * @param livreur the livreur to set
	 */
	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	
	/**
	 * @return the pizzas
	 */
	public List<Pizza> getPizzas() {
		return pizzas;
	}

	/**
	 * @param pizzas the pizzas to set
	 */
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
}
