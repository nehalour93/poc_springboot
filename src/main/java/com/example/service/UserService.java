package com.example.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.model.User;

@Service
public interface UserService  {

	List<User> findAll();
	
	User save(User user);

	User getById(long id);

	User updateAccountStatus(String userType, User user);

}
