package org.example.ws.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import service.UserService;

public class TestContext {
	
	  @Bean
	    public UserService todoService() {
	        return Mockito.mock(UserService.class);
	    }

}
