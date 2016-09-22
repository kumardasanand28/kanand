package com.spring.advice;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdvisor  implements MethodBeforeAdvice{
	
	static Logger log = Logger.getLogger(BeforeAdvisor.class.getName()); 

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		log.info("Calling "+method.getName());
		log.info("Args "+args);
		System.out.println("Calling "+method.getName());
		
	}

}
