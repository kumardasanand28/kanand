package com.singleton;

import static javax.ejb.ConcurrencyManagementType.BEAN;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.Singleton;
import javax.ejb.Startup;


@Singleton
@Startup
@ConcurrencyManagement(BEAN)//Bean manages thread safety  on his own
public class PropertyRegistryBeanManaged {


	private final Properties properties = new Properties();

	@PostConstruct
	public void applicationStartup() {
		System.out.println("applicationStartup : PropertyRegistryBeanManaged");
		properties.putAll(System.getProperties());
	}


	@PreDestroy
	public void applicationShutdown() {
		System.out.println("shutdown : PropertyRegistryBeanManaged");
		properties.clear();
	}


	public String getProperty(final String key) {
		return properties.getProperty(key);
	}
	
	
	public Properties getProperties() {
		return properties;
	}
}
