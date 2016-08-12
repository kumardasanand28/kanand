package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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
