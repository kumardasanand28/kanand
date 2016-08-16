package org.example.ws.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;

import org.example.ws.config.TestContext;
import org.example.ws.config.WebAppContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import controller.UserController;
import model.User;
import repository.UserRepositoryImpl;
import service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
public class UserTestService {

	static final String HOST = "http://localhost:8090/";
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	public static String[] userDetailsList = {"testuser@gmail.com","testuser","testpassword"};

	static final String HOME_PAGE = "main";
	
	static final String ADMIN_PAGE = "userlist";

	@Bean
	public UserService userService() {
		return Mockito.mock(UserService.class);
	}

	@Bean
	public UserController userController(){
		return new UserController();
	}

	@Bean
	public UserRepositoryImpl getuserRepImpl(){
		return new UserRepositoryImpl();
	}


	@Test
	public void verify_load_Home_Page() throws Exception{
		getMockController().perform(get(HOST)).andExpect(status().isOk()).andExpect(view().name(HOME_PAGE))
		.andDo(print());
	}


	@Test
	public void verify_Create_user() throws Exception{

		User user = new User();
		user.setEmail(userDetailsList[0]);
		user.setNickname(userDetailsList[1]);
		user.setPassword(userDetailsList[2]);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson=ow.writeValueAsString(user);

		getMockController().perform(post(HOST+"user/").contentType(APPLICATION_JSON_UTF8).content(requestJson))
						   .andExpect(status().isOk())
						   .andDo(print())
						   .andExpect(view().name(HOME_PAGE));
	}

	@Test
	public void verify_Fetch_All_Users() throws Exception{

		getMockController().perform(get(HOST+"fetchregisteredusers/"))
														.andExpect(status().isOk())
														.andExpect(view().name(ADMIN_PAGE))
														.andExpect(model().attributeExists("userlist"))
														.andDo(print());

	}
		
	@Test
	public void verify_Remove_Specific_user() throws Exception{
	
		getMockController().perform(post(HOST+"removeuser/").content( userDetailsList[0]))
															.andExpect(status().isOk())
															.andExpect(view().name(ADMIN_PAGE))
															.andDo(print());
		
	}
	
	
	
	
	
	public MockMvc getMockController(){
		return MockMvcBuilders.standaloneSetup(this.userController()).build();
	}
	
	
	
	
}
