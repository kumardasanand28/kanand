package com.spring.advice;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

public class AfterAdvisor  implements AfterReturningAdvice{

	static Logger log = Logger.getLogger(AfterAdvisor.class.getName()); 
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		log.info("Returned "+method.getName());
		System.out.println("Returned "+method.getName());
		
	}

}
