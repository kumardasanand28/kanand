package service;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import model.User;

public abstract class UserService {
	
	public abstract ModelAndView welcome();
	public abstract ModelAndView fetchRegisteredUsers();
	public abstract ModelAndView createUser(@RequestBody User user);
	public abstract ModelAndView removeUser(@RequestBody String email);

}
