package com.singleton;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class PropertyLoader {


	private Properties properties;

	@PostConstruct
	public void  propertyLoader() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("/META-INF/testing.properties"));
			if(properties != null){
				System.out.println(properties);	
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
