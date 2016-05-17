package fr.pizzeria.dao;

public interface DaoFactory {
	IPizzaDao getPizzaDao();
	IClientDao getClientDao();
	ICommandeDao getCommandeDao();
}
