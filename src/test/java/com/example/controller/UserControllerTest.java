package com.example.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.controller.UserController;
import com.example.model.User;
import com.example.service.UserService;
import com.example.util.Conversion;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@MockBean
	private Conversion conversion;
	

	User mockUser = new User(1, "Neha", "admin", "ACTIVATED");

	String exampleUserJson = "{\"name\":\"Neha\",\"userType\":\"admin\",\"accountStatus\":\"ACTIVATED\"}";

	@Test
	public void createUser() throws Exception {
		User mockUser = new User(1, "Neha", "admin", "ACTIVATED");

		String exampleUserJson = "{\"id\":4,\"name\":\"Neha\",\"userType\":\"merchant\",\"accountStatus\":\"DEACTIVATED\"}";

		Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(mockUser);
		

		// Send user as body to /api/user/
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/").accept(MediaType.APPLICATION_JSON)
				.content(exampleUserJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void retrieveUserDetails() throws Exception {

		Mockito.when(userService.getById(Mockito.anyInt())).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/1").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,name:Neha,userType:admin,accountStatus:ACTIVATED}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void updateUser() throws Exception {
		User mockUser = new User(1, "Neha", "admin", "ACTIVATED");

		String exampleUserJson = "{\"id\":1,\"name\":\"Neha\",\"userType\":\"merchant\",\"accountStatus\":\"DEACTIVATED\"}";

		Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(mockUser);

		// Send user as body to /api/user/
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/1").accept(MediaType.APPLICATION_JSON)
				.content(exampleUserJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test public void retrieveAllUserDetails() throws Exception {
	  
	  List<User> users = new ArrayList<>();
	  
	  Mockito.when(userService.findAll());
	  
	  RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/").accept(MediaType.
	  APPLICATION_JSON);
	  
	  MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	  
	  System.out.println(result.getResponse()); 
	  
	  String expected = "{id:1,name:Neha,userType:admin,accountStatus:ACTIVATED}";
	  
	  JSONAssert.assertEquals(expected,
	  result.getResponse().getContentAsString(), false); 
	  }

}
