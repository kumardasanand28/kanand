package org.example.ws.service;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.example.ws.config.TestContext;
import org.example.ws.config.WebAppContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import repository.UserRepositoryImpl;
import service.UserService;

//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextHierarchy(@ContextConfiguration)
@WebIntegrationTest
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
public class AddUserTestService {



	private MockMvc mockMvc;

	//Required to Generate JSON content from Java objects
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


	//Test RestTemplate to invoke the APIs.
	private RestTemplate restTemplate = new TestRestTemplate();

	@Autowired
	private UserService userService;

	private User user = new User();

	@Autowired
	private UserRepositoryImpl repository;



	@Test
	public void verify_Registration() throws Exception{

		User user = new User("abcd@gmail.com","Sample","Sample");
		repository.save(user);
		
		//restTemplate.postForObject("http://localhost:8090/user/", user, Mode, uriVariables)
		

	}



}
