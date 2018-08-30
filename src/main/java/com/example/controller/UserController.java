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

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		logger.info("creating user");
		User user = conversion.convertToUserEntity(userDto);
		userService.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto payload) {
		User user1 = conversion.convertToUserEntity(payload);
		userService.getById(payload.getId());
		userService.save(user1);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDto> getUser(@PathVariable("id") int id) {
		UserDto userDto = conversion.convertToUserDto(userService.getById(id));
		return new ResponseEntity<>(userDto, HttpStatus.FOUND);

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDto> getAllUsersList() {
		logger.info("getting users list");
		List<User> users = userService.findAll();
		List<UserDto> userlist = users.stream().map(user -> conversion.convertToUserDto(user))
				.collect(Collectors.toList());
		return userlist;
	}

	@RequestMapping(value = "/{userType}", method = RequestMethod.POST)
	public ResponseEntity<UserDto> accountStatus(@PathVariable("userType") String userType,
			@RequestBody UserDto userDto) {
		User user = conversion.convertToUserEntity(userDto);
		userService.updateAccountStatus(userType, user);
		return new ResponseEntity<>(userDto, HttpStatus.OK);

	}

}
