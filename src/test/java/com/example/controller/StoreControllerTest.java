/*package com.example.controller;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.model.Payment;
import com.example.model.Store;
import com.example.model.User;
import com.example.service.StoreService;
import com.example.util.Conversion;
import org.json.JSONObject;
@RunWith(SpringRunner.class)
@WebMvcTest(value = StoreController.class, secure = false)
public class StoreControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StoreService storeService;
	
	@MockBean
	private Conversion conversion;
	
	public static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	

	Store mockStore = new Store(1, "More", 2 , Arrays.asList(""));
	
	int id, String storeName, int merchantId, Set<Payment> payments

	String exampleUserJson = "{\"id\":4,\"name\":\"Neha\",\"userType\":\"admin\",\"accountStatus\":\"ACTIVATED\"}";

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
		
		System.out.println(mockUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/1").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,name:Neha,userType:admin,accountStatus:ACTIVATED}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	

	public void retrieveUserDetails() throws Exception {

		Mockito.when(
				userService.retrieveCourse(Mockito.anyString(),
						Mockito.anyString())).thenReturn(mockCourse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/students/Student1/courses/Course1").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:Course1,name:Spring,description:10 Steps}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
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
	  
	  Mockito.when(userService.findAll()).thenReturn(users);
	  
	  RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/").accept(MediaType.
	  APPLICATION_JSON);
	  
	  MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	  
	  System.out.println(result.getResponse()); 
	  
	 String expected = "{id:1,name:Neha,userType:admin,accountStatus:ACTIVATED}";
	 JSONObject jsonObject = new JSONObject(expected);
	  
	  JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(), false); 
	  }

}

*/