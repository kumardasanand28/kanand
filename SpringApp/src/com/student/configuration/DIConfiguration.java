package com.student.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.student.repository.UserRepository;
import com.student.service.RegistrationService;
import com.student.springjpa.service.UserService;
import com.student.springjpa.service.impl.UserServiceImpl;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"com.student.springjpa"})
@ComponentScan(basePackages = {"com.student","com.student.repository"})
@Import({MessagingConfiguration.class,MessagingListnerConfiguration.class})
public class DIConfiguration{

	@Autowired
	JmsTemplate jmsTemplate;

	@Bean
	public InternalResourceViewResolver  viewResolver(){
		InternalResourceViewResolver  resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public RegistrationService getRegService(){
		RegistrationService regService = new RegistrationService();
		regService.setJmsTemplate(jmsTemplate);
		regService.setTestQueue(getQueue());
		return regService;
	}

	@Bean
	public ActiveMQQueue getQueue(){
		ActiveMQQueue queue = new ActiveMQQueue();
		queue.setPhysicalName("TestQueue");
		return queue;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.student.entity" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}


	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mycompany");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		return dataSource;
	}


	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}
	@Bean
	public UserRepository getUserRepository(){
		return new UserRepository();
	}
	
	@Bean
	public UserService getUserService(){
		return new UserServiceImpl();
	}

}
