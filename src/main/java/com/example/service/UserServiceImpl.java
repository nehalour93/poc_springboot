package com.example.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.CustomException;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User getById(int id) {
		if (userRepository.findById(id) != null)
			throw new CustomException("user with id " + id + " does not exist");
		return userRepository.getOne(id);

	}

	@Override
	public List<User> findAll() {
		logger.info("getting all users");
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			logger.info("getting all users1");
			throw new CustomException("User not found");
		}
		logger.info("getting all users2");
		return users;
	}

	@Override
	public User save(User user) {
		if (user.getName().equals("")) {
			throw new CustomException("name cannot be null");
		} else if (user.getUserType().equalsIgnoreCase("admin")) {
			user.setAccountStatus("ACTIVATED");
		} else {
			user.setAccountStatus("DEACTIVATED");
		}
		return userRepository.save(user);
	}

	@Override
	public User updateAccountStatus(String userType, User user) {
		if (userType.equalsIgnoreCase("admin")) {
			int id = user.getId();
			if (getById(id) == null)
				throw new CustomException("Unable to Update. A User with id " + id + " doesn't exist.");
			else {
				userRepository.save(user);
			}
		} else {
			throw new CustomException("You do not have permission to activate/deactivate the account");
		}
		return user;
	}

	@Override
	public User findById(int id) {
		if (userRepository.findById(id) == null) {
			throw new CustomException("user with id " + id + " does not exist");
		}
		return userRepository.getOne(id);
	}


}
