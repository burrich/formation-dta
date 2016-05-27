package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

@Stateless
public class PizzaService {
	@PersistenceContext(unitName="pizzeria-admin-app") private EntityManager em;
	
	public List<Pizza> findAllPizzas() throws DaoException {
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p", Pizza.class);
		
		return query.getResultList();
	}

	public Pizza findByCode(String code) throws DaoException {
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code=:code", Pizza.class);
		query.setParameter("code", code);
		
		return query.getSingleResult();
	}
	
	public boolean savePizza(Pizza newPizza) throws DaoException {
		em.persist(newPizza);
		return true;
	}
	
	public boolean updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		Pizza toUpdatePizza = find(codePizza, em);
		toUpdatePizza.setNom(updatePizza.getNom());
		toUpdatePizza.setPrix(updatePizza.getPrix());
		toUpdatePizza.setCategorie(updatePizza.getCategorie());
		
		return true;
	}
	
	private Pizza find(String code, EntityManager em) {
		return em.createNamedQuery("pizza.findByCode", Pizza.class)
				.setParameter("code", code)
				.getSingleResult();
	}
	
	public boolean deletePizza(String codePizza) throws DaoException {
		Pizza deletePizza = find(codePizza, em);
		em.remove(deletePizza);
		
		return true;
	}
}
