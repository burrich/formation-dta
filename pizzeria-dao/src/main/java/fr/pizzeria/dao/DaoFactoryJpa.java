package fr.pizzeria.dao;

import javax.persistence.EntityManagerFactory;

public class DaoFactoryJpa implements DaoFactory {

	private IPizzaDao pizzaDao;
	private IClientDao clientDao;
	private ICommandeDao commandeDao;
	
	public DaoFactoryJpa(EntityManagerFactory emf) {
		this.pizzaDao = new PizzaDaoJpa(emf);
		this.clientDao = new ClientDaoJpa(emf);
		this.commandeDao = new CommandeDaoJpa(emf);
	}
	
	@Override
	public IPizzaDao getPizzaDao() {
		return this.pizzaDao;
	}

	@Override
	public IClientDao getClientDao() {
		return this.clientDao;
	}

	@Override
	public ICommandeDao getCommandeDao() {
		return commandeDao;
	}
}
