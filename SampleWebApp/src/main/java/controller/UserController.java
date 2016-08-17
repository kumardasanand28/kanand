package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.User;
import repository.UserRepositoryImpl;
import service.UserService;

@Controller
public class UserController extends UserService {

	@Autowired
	UserRepositoryImpl userRepository;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String welcome() {
		return "UserManagement";
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
	public ResponseEntity<Void> createUser(@RequestBody User user) {

		String emailid = user.getEmail();
		try {
			User existingUser = userRepository.findUser(emailid);
			if(existingUser != null){
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}

			userRepository.save(user);

			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@RequestMapping(value="/removeuser/", method=RequestMethod.POST)
	public ResponseEntity<User> removeUser(@RequestBody String id) {

		try {

			User user = userRepository.findUser(id);
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
