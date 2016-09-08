package com.anootations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.HelloWorld;

@Configuration
public class HelloWorldConfig {
	
	@Bean
	public HelloWorld helloWorld(){
		return new HelloWorld();
	}

}
