package fr.pizzeria.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import fr.pizzeria.model.Commande;

public class CommandeDaoJpa implements ICommandeDao {

	private EntityManagerFactory emFactory;
	
	
	/**
	 * @param emFactory
	 */
	public CommandeDaoJpa(EntityManagerFactory emFactory) {
		this.emFactory = emFactory;
	}


	@Override
	public void saveCommande(Commande newCommande) {
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(newCommande);
		et.commit();
		em.close();
	}

}
