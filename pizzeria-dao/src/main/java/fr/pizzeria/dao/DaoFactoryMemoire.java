package fr.pizzeria.dao;

public class DaoFactoryMemoire implements DaoFactory {

	private IPizzaDao pizzaDao;
	private IClientDao clientDao;
	
	public DaoFactoryMemoire() {
		this.pizzaDao = new PizzaDaoImpl();
		this.clientDao = null;
	}

	@Override
	public IPizzaDao getPizzaDao() {
		return pizzaDao;
	}

	@Override
	public IClientDao getClientDao() {
		return this.clientDao;
	}

	@Override
	public ICommandeDao getCommandeDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
