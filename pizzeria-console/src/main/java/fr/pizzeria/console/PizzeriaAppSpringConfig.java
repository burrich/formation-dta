package fr.pizzeria.console;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.dao.PizzaDaoJpa;

@Configuration
@ComponentScan("fr.pizzeria")
@EnableTransactionManagement
public class PizzeriaAppSpringConfig {

//	@Bean
//	public IPizzaDao pizzaDao() {
//		return new PizzaDaoImpl();
//	}
	
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
//	}~
	
//	@Bean
//	public EntityManagerFactory emf() {
//		return Persistence.createEntityManagerFactory("pizzeria-console");
//	}
	
	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
	
	@Bean
	public PropertyPlaceholderConfigurer props() {
		PropertyPlaceholderConfigurer co = new PropertyPlaceholderConfigurer();
		co.setLocation(new ClassPathResource("jdbc.properties"));
		return co;
	}
	
	@Bean
	public DataSource dataSource(@Value("${jdbc.url}") String url, @Value("${jdbc.user}") String user, @Value("${jdbc.password}") String motDePasse) {
		return new DriverManagerDataSource(url, user, motDePasse);
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}
