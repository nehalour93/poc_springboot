package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.example.model.User;
import com.example.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllUser() {
		List<User> userList = new ArrayList<User>();
		userList.add(new User(1, "Neha", "admin","AcTIVATED"));
		userList.add(new User(2, "abc", "merchant","DEACTIVATED"));
		userList.add(new User(3, "xyz", "admin","ACTIVATED"));
		when(userRepository.findAll()).thenReturn(userList);

		List<User> result = userServiceImpl.findAll();
		assertEquals(3, result.size());
	}

	@Test
	public void testGetUserById() {
		User user = new User(1, "Neha", "admin","ACTIVATED");
		when(userRepository.getOne(1L)).thenReturn(user);
		User result = userServiceImpl.getById(1);
		assertEquals(1, result.getId());
		assertEquals("Neha", result.getName());
		assertEquals("admin", result.getUserType());
		assertEquals("ACTIVATED", result.getAccountStatus());
	}

	
	@Test
	public void saveUser() {
		User user = new User(1, "Neha", "admin","ACTIVATED");
		when(userRepository.save(user)).thenReturn(user);
		User result = userServiceImpl.save(user);
		assertEquals(1, result.getId());
		assertEquals("Neha", result.getName());
		assertEquals("admin", result.getUserType());
		assertEquals("ACTIVATED", result.getAccountStatus());
	}

}
