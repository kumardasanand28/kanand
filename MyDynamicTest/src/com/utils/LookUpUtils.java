package com.utils;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class LookUpUtils {
	
	
	
	public Object lookUp(String beanUrl){
		Object bean = null;
		Properties jndiProps1 = new Properties();
		jndiProps1.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
		jndiProps1.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		try {
			Context ctx = new InitialContext(jndiProps1);
			bean = (Object) ctx.lookup(beanUrl);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

}
