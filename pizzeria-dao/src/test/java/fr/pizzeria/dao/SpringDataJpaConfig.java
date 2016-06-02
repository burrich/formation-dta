package fr.pizzeria.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Configuration
@ComponentScan("fr.pizzeria")
@EnableJpaRepositories("fr.pizzeria.dao")
@EnableTransactionManagement
public class SpringDataJpaConfig {
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("db-schema.sql")
				.addScript("db-data.sql").build();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() { // DataSource dataSource
		return new JpaTransactionManager();
	}
	
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emfBean = new LocalEntityManagerFactoryBean();
		emfBean.setPersistenceUnitName("pizzeria-pu");
		return emfBean;
	}
}
