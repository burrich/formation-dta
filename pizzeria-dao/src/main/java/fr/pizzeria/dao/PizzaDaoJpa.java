package fr.pizzeria.dao;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.ListUtils;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {

	private EntityManagerFactory emFactory;
	
	/**
	 * @param em
	 */
	public PizzaDaoJpa(EntityManagerFactory emFactory) {
		this.emFactory = emFactory;
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		EntityManager em = emFactory.createEntityManager();
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p", Pizza.class);
		
		return query.getResultList();
	}

	@Override
	public boolean savePizza(Pizza newPizza) throws DaoException {
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(newPizza);
		et.commit();
		em.close();
		
		return true;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			Pizza toUpdatePizza = find(codePizza, em);
			toUpdatePizza.setNom(updatePizza.getNom());
			toUpdatePizza.setPrix(updatePizza.getPrix());
			toUpdatePizza.setCategorie(updatePizza.getCategorie());
			et.commit();
		} finally {
			em.close();
		}
		
		return true;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DaoException {
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			Pizza deletePizza = find(codePizza, em);
			em.remove(deletePizza);
			et.commit();
		} finally {
			em.close();
		}
		
		return true;
	}
	
	//TODO: remove
	private Pizza find(String code, EntityManager em) {
		return em.createNamedQuery("pizza.findByCode", Pizza.class)
				.setParameter("code", code)
				.getSingleResult();
	}
	
	public boolean saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {
		EntityManager em = emFactory.createEntityManager();
		
		listPizzas.sort(Comparator.comparing(Pizza::getCode));
		
		ListUtils.partition(listPizzas, nb).forEach(list -> {
			
		});
		
		return true;
	}

	@Override
	public Pizza findByCode(String code) throws DaoException {
		EntityManager em = emFactory.createEntityManager();
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code=:code", Pizza.class);
		query.setParameter("code", code);
		
		return query.getSingleResult();
	}
}
