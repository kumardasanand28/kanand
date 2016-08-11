package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import model.User;
import repository.UserRepositoryImpl;

@Controller
public class UserController {

	@Autowired
	private UserRepositoryImpl userRepository;

	@RequestMapping("/")
	public String welcome() {
		return "main";
	}
	
	

	@RequestMapping(value="/user/", method=RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		
		System.out.println("Creating User " + user.getEmail());
		
		try {
			userRepository.save(user);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);	
		

	}

}
