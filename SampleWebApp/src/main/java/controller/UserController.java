package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import repository.UserRepositoryImpl;
import service.UserService;

@Controller
public class UserController extends UserService {

	private final UserRepositoryImpl userRepository = new UserRepositoryImpl();;
	
	@RequestMapping("/")
	public ModelAndView welcome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
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
	public ModelAndView createUser(@RequestBody User user) {
		ModelAndView mav = new ModelAndView();
		try {
			userRepository.save(user);

			mav.setViewName("main");
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return mav;
	}


	@RequestMapping(value="/removeuser/", method=RequestMethod.POST)
	public ModelAndView removeUser(@RequestBody String email) {
		ModelAndView mav = new ModelAndView();

		try {
			User user = userRepository.findUser(email);
			userRepository.remove(user);
			mav.setViewName("userlist");
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return mav;
	}

}
