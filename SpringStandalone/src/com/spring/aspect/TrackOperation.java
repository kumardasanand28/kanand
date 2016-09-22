package com.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TrackOperation {

	@Pointcut("execution(* com.spring.bean.SpringBean.*(..))")  
	public void start(){
		System.out.println("Started");
	}

	@Before("start()")
	public void myAdvise(JoinPoint joinPoint){
		System.out.println("additional concern");  
	}

	@After("start()")
	public void postMyAdvise(JoinPoint joinPoint){
		System.out.println("POST additional concern");  
	}


	@AfterReturning(pointcut="execution(* com.spring.bean.SpringBean.*(..))",returning="result")
	public void afterReturningAdvise(JoinPoint joinPoint,Object result){
		System.out.println("additional concern");  
		System.out.println("Method Signature: "  + joinPoint.getSignature());  
		System.out.println("Result in advice: "+result);  
		System.out.println("end of after returning advice...");  
	}

	@Around("start()")
	public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable   
	{  
		System.out.println("Additional Concern Before calling actual method");  
		Object obj=pjp.proceed();  
		System.out.println("Additional Concern After calling actual method");  
		return obj;  
	}
}
