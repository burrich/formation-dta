package fr.pizzeria.dao;

import fr.pizzeria.model.Commande;

public interface ICommandeDao {
	void saveCommande(Commande newCommande);
}
