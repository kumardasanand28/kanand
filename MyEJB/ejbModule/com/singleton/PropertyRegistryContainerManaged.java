package com.singleton;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.Singleton;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

@Singleton
@Lock(READ)//allows multi thread access
public class PropertyRegistryContainerManaged {


	private final Properties properties = new Properties();

	@PostConstruct
	public void applicationStartup() {
		System.out.println("applicationStartup : PropertyRegistryContainerManaged");
		properties.putAll(System.getProperties());
	}


	@PreDestroy
	public void applicationShutdown() {
		System.out.println("shutdown : PropertyRegistryContainerManaged");
		properties.clear();
	}


	public String getProperty(final String key) {
		return properties.getProperty(key);
	}


	@Lock(WRITE)//thread safe
	public String setProperty(final String key, final String value) {
		return (String) properties.setProperty(key, value);
	}

	@Lock(WRITE)
	public String removeProperty(final String key) {
		return (String) properties.remove(key);
	}
}
