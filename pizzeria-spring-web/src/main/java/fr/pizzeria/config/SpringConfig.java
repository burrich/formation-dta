package fr.pizzeria.config;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({"fr.pizzeria.controller", "fr.pizzeria.aspect"})
@EnableJpaRepositories("fr.pizzeria.repo")
@EnableAspectJAutoProxy
public class SpringConfig {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver irvResolver = new InternalResourceViewResolver();
		irvResolver.setPrefix("/WEB-INF/views/");
		irvResolver.setSuffix(".jsp");
		return irvResolver;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);

	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan("fr.pizzeria.model");
	    factory.setPersistenceUnitName("pizzeria-spring-web");
	    factory.afterPropertiesSet();
	    
	    return factory.getObject();
	}
}
