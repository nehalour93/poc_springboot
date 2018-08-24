package com.example.POC.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.POC.model.User;
import com.example.POC.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public ResponseEntity<String> createUser(@RequestBody User user) {
		String msg = null;
		int id = user.getId();
		if (userRepository.findById(id) != null) {
			msg = "Unable to create. A User with id " + id + " already exist.";
			logger.error(msg);
			return new ResponseEntity<>(msg, HttpStatus.CONFLICT);

		} else {
			msg = "User created";
			logger.info("Creating User : {}", user);
			String userTyp = user.getUserType();
			if (userTyp.equalsIgnoreCase("admin")) {
				user.setAccountStatus("ACTIVATED");
			} else {
				user.setAccountStatus("DEACTIVATED");
			}
			userRepository.save(user);

		}

		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		String msg = null;
		int id = user.getId();
		if (userRepository.findById(id) == null) {
			msg = "Unable to Update. A User with name " + user.getId() + " doesn't exist";
			logger.error(msg);
			return new ResponseEntity<>(msg, HttpStatus.CONFLICT);

		}

		else {
			msg = "User updated";
			logger.info("Updating User : {}", user);
			userRepository.save(user);

		}

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		String msg = null;
		User user = userRepository.findById(id);
		if (user == null) {
			msg = "Unable to retrieve. A User with id " + id + " doesn't exist.";
			logger.error(msg);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

		else {
			logger.info("Retrieving User : {}", id);
			userRepository.getById(id);
		}

		return new ResponseEntity<>(user, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/account/{userType}", method = RequestMethod.POST)
	public ResponseEntity<String> accountStatus(@PathVariable("userType") String userType, @RequestBody User user) {
		String msg = null;
		if (userType.equalsIgnoreCase("admin")) {
			int id = user.getId();
			if (userRepository.findById(id) == null) {
				msg = "Unable to Update. A User with id " + id + " doesn't exist.";
				logger.error(msg);
				return new ResponseEntity<>(msg, HttpStatus.CONFLICT);

			} else {
				msg = "Account status of user with id " + user.getId() + " is updated";
				logger.info("Updating User : {}", user);
				userRepository.save(user);
				return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
			}
		} else {
			msg = "You do not have permission to activate/deactivate the account";
			return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
		}

	}
}
