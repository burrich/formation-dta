package fr.pizzeria.console;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.dao.PizzaDaoJpa;

@Configuration
@ComponentScan("fr.pizzeria")
public class PizzeriaAppSpringConfig {

	@Bean
	public IPizzaDao pizzaDao() {
		return new PizzaDaoImpl();
	}
	
//	@Bean
//	public PizzaDaoJDBCImpl pizzaDaoJDBCImpl() throws DaoException {
//		ResourceBundle jdbcBundle = ResourceBundle.getBundle("jdbc");
//		String driver = jdbcBundle.getString("jdbc.driver");
//		String url = jdbcBundle.getString("jdbc.url");
//		String user = jdbcBundle.getString("jdbc.user");
//		String password = jdbcBundle.getString("jdbc.password");
//		
//		return new PizzaDaoJDBCImpl(driver, url, user, password);
//	}
	
//	@Bean
//	public PizzaDaoJpa pizzaDaoJpa(EntityManagerFactory emf) {
//		return new PizzaDaoJpa(emf);
//	}
	
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
	
	@Bean
	public EntityManagerFactory emf() {
		return Persistence.createEntityManagerFactory("pizzeria-console");
	}
}
