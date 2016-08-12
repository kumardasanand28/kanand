package service;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import model.User;

public abstract class UserService {
	
	public abstract String welcome();
	public abstract ModelAndView fetchRegisteredUsers();
	public abstract String createUser(@RequestBody User user);
	public abstract String removeUser(@RequestBody String email);

}
