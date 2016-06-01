package fr.pizzeria.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
public class BatchPizzaDaoJDBCTemplate {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BatchPizzaDaoJDBCTemplate(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insertListPizzas(List<Pizza> list3Pizzas) throws DaoException {
		for (Pizza pizza : list3Pizzas) {
			if (pizza.getCode().equals("ORI")) {
				throw new DaoException();
			}
			savePizza(pizza);
		}
	}
	
	private boolean savePizza(Pizza newPizza) throws DaoException {
		String sql = "INSERT INTO pizza(reference, libelle, prix, categorie) "
				+ "VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sql, newPizza.getCode(), newPizza.getNom(), newPizza.getPrix(), newPizza.getCategorie().name());
		
		return true;
	}
}
