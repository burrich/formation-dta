package fr.pizzeria.dao;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Client;

/**
 * CRUD Client
 * @author Nicolas
 *
 */
public interface IClientDao {
	void saveClient(Client newClient) throws DaoException;
	boolean connection(String mail, String psw);
}
