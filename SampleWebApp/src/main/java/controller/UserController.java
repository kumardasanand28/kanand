package controller;

import java.util.ArrayList;
import java.util.List;

import model.User;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import repository.UserRepositoryImpl;
import service.UserService;

@RestController
public class UserController extends UserService {

	@Autowired
	UserRepositoryImpl userRepository;

	@RequestMapping("/")
	public String welcome() {
		return "main";
	}


	@RequestMapping(value="/listusers", method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		try {
			List<User> registeredUserList = new ArrayList<User>();
			registeredUserList.addAll(userRepository.findAll());

			if(registeredUserList.size() == 0){
				return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<User>>(registeredUserList,HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}



	@RequestMapping(value="/user/", method=RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user,UriComponentsBuilder ucBuilder) {

		String emailid = user.getEmail();
		try {
			User existingUser = userRepository.findUser(emailid);
			if(existingUser != null){
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}

			userRepository.save(user);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@RequestMapping(value="/removeuser/", method=RequestMethod.POST)
	public ResponseEntity<User> removeUser(@RequestBody String email) {

		try {

			User user = userRepository.findUser(email);
			if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}

			userRepository.remove(user);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
	}




}
