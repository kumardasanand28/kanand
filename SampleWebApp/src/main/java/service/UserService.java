package service;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import model.User;

public abstract class UserService {
	
	public abstract String welcome();
	public abstract ResponseEntity<List<User>> listAllUsers();
	public abstract ResponseEntity<Void> createUser(@RequestBody User user);
	public abstract ResponseEntity<User> removeUser(@RequestBody String email);

}
