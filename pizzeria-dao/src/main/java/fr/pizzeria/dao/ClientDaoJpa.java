package fr.pizzeria.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Client;

public class ClientDaoJpa implements IClientDao {

	private EntityManagerFactory emFactory;
	
	/**
	 * @param emFactory
	 */
	public ClientDaoJpa(EntityManagerFactory emFactory) {
		this.emFactory = emFactory;
	}


	@Override
	public void saveClient(Client newClient) throws DaoException {
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(newClient);
		et.commit();
		em.close();
	}


	@Override
	public boolean connection(String mail, String psw) {
		EntityManager em = emFactory.createEntityManager();
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE email=:mail AND motDePasse=:psw", Client.class);
		query.setParameter("mail", mail);
		query.setParameter("psw", psw);
		
		try {
			query.getSingleResult();
		} catch (NoResultException e) {
			return false;
		}
		
		return true;
	}
}
