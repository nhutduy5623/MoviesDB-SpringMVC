package com.laptrinhweb.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;




@Configuration
@EnableJpaRepositories(basePackages = {"com.laptrinhweb.repository"})
@EnableTransactionManagement
@ComponentScan(basePackages ={"com.laptrinhweb"})
public class JPAConfig {
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPersistenceUnitName("MoviesDB_persistence-data");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/db_moviesdb_jpa");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		return dataSource;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
//		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//		 properties.setProperty("hibernate.hbm2ddl.auto", "none");
		 
		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		
//		<!-- Lưu ý phiên bản dialect MySQL8Dialect "MySQL(8)" -->
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.use_sql_comments", "true");
		properties.setProperty("org.hibernate.SQL", "debug");
		properties.setProperty("org.hibernate.type.descriptor.sql.BasicBinder", "debug");
		return properties;
	}
}
