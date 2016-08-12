package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import service.*;

import model.User;
import repository.UserRepositoryImpl;

@Controller
public class UserController extends UserService {

	@Autowired
	private UserRepositoryImpl userRepository;

	@RequestMapping("/")
	public String welcome() {
		return "main";
	}


	@RequestMapping(value="/fetchregisteredusers", method=RequestMethod.GET)
	public ModelAndView fetchRegisteredUsers() {
		try {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("userlist");

			List<User> registeredUserList = new ArrayList<User>();
			registeredUserList.addAll(userRepository.findAll());
			mav.addObject("userlist", registeredUserList);
			return mav;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return null;
	}



	@RequestMapping(value="/user/", method=RequestMethod.POST)
	public String createUser(@RequestBody User user) {

		try {
			userRepository.save(user);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return "main";
	}
	
	
	@RequestMapping(value="/removeuser/", method=RequestMethod.POST)
	public String removeUser(@RequestBody String email) {


		try {
			System.out.println(email);
			User user = userRepository.findUser(email);
			userRepository.remove(user);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return "userlist";
	}

}
