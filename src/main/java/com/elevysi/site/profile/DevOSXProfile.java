package com.elevysi.site.profile;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Profile("devOSX")
@Configuration
@PropertySource("classpath:META-INF/application-devOSX.properties")
//@PropertySource("classpath:/com/acme/app.properties")
//@ComponentScan({"com.elevysi.site.entity"})
public class DevOSXProfile {
	
	@Autowired
	private Environment environment;
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.elevysi.site.entity" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        sessionFactory.setJpa
        
        return sessionFactory;
     }
	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.elevysi.site.entity" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(hibernateProperties());
	 
	      return em;
    }
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    
		dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
		dataSource.setUrl(environment.getRequiredProperty("db.url"));
		dataSource.setUsername(environment.getRequiredProperty("db.username"));
	    dataSource.setPassword(environment.getRequiredProperty("db.password"));
	    
	     
	    return dataSource;
	}
	
	
	 private Properties hibernateProperties() {
		 
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//	        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	        properties.put("hibernate.max_fetch_depth", environment.getRequiredProperty("hibernate.max_fetch_depth"));
	        properties.put("hibernate.fetch_size", environment.getRequiredProperty("hibernate.fetch_size"));
	        properties.put("hibernate.batch_size", environment.getRequiredProperty("hibernate.batch_size"));
	        return properties;        
    }
	     
//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(SessionFactory s) {
//       HibernateTransactionManager txManager = new HibernateTransactionManager();
//       txManager.setSessionFactory(s);
//       return txManager;
//    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
  
       return transactionManager;
    }
	

}
