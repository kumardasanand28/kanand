package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.bean.SpringBean;

public class SpringTest {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("Beans.xml");
		
		//SpringBean bean = (SpringBean) context.getBean("proxy",SpringBean.class);
		SpringBean bean = (SpringBean) context.getBean("springtest");
		bean.testInjection();
	}

}
