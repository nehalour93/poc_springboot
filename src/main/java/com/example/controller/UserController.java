package com.example.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.UserDto;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.Conversion;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Conversion conversion;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public UserDto createUser(@RequestBody UserDto userDto) {
		logger.info("creating user");
		User user = conversion.convertToUserEntity(userDto);
		User userCreated = userService.save(user);
		return conversion.convertToUserDto(userCreated);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateUser(@RequestBody UserDto userDto, @PathVariable("id") int id) {
		User userToBeUpdated = userService.findById(id);
		if (userToBeUpdated != null) {
			User user = conversion.convertToUserEntity(userDto);
			userService.save(user);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public UserDto getUser(@PathVariable("id") int id) {
		return conversion.convertToUserDto(userService.getById(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDto> getAllUsersList() {
		logger.info("getting users list");
		List<User> users = userService.findAll();
		logger.info("return");
		List<UserDto> userlist = users.stream().map(user -> conversion.convertToUserDto(user))
				.collect(Collectors.toList());
		logger.info("userlist");
		return userlist;
	}
	
	@RequestMapping(value = "/{userType}", method = RequestMethod.POST)
	public ResponseEntity<UserDto> accountStatus(@PathVariable("userType") String userType, @RequestBody UserDto userDto) {
		User user = conversion.convertToUserEntity(userDto);
		userService.updateAccountStatus(userType,user);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
		
	}

}
