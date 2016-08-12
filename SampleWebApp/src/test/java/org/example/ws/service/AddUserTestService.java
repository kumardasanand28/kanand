package org.example.ws.service;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import model.User;

import service.UserService;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy(@ContextConfiguration)
@WebAppConfiguration
public class AddUserTestService {

	final String BASE_URL = "http://localhost:9080/";

	private MockMvc mockMvc;

	@Autowired
	private UserService userService;

	@Autowired
	private User user = new User();


	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(userService).build();

		user.setId("1");
		user.setEmail("abcd@gmail.com");
		user.setCreatedDate(new Date());
		user.setNickname("Sample NickName");
		user.setPassword("SamplePassword");

	}


	@Test
	public void main_Page_Render() throws Exception {

		this.mockMvc.perform(post(BASE_URL)).andExpect(status().isOk()).andExpect(forwardedUrl("/main"));

	}



}
