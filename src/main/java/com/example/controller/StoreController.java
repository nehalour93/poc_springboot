package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.example.dto.StoreDto;
import com.example.dto.UserDto;
import com.example.exception.CustomException;
import com.example.model.Store;
import com.example.model.User;
import com.example.service.StoreService;
import com.example.service.UserService;
import com.example.util.Conversion;

@RestController
@RequestMapping("/api/store")
public class StoreController {

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private Conversion conversion;

	public static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public StoreDto createStore(@RequestBody StoreDto storeDto) {
		Store storeCreated = null;
		Store store = conversion.convertToStoreEntity(storeDto);
		if (userService.getById(store.getMerchantId()) != null)
			storeCreated = storeService.save(store);
		else {
			throw new CustomException("Unable to create store for merchantId " + store.getMerchantId()
					+ " since user with this merchantId does not exist.");
		}
		return conversion.convertToStoreDto(storeCreated);
	}

	@RequestMapping(value = "/createStores", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public List<StoreDto> createStores(@RequestBody List<StoreDto> storesDto) {
		List<Store> store = storesDto.stream().map(storeDto -> conversion.convertToStoreEntity(storeDto))
				.collect(Collectors.toList());
		List<Store> storeCreated = storeService.saveAll(store);
		return storeCreated.stream().map(st -> conversion.convertToStoreDto(st)).collect(Collectors.toList());
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<StoreDto> updateStore(@RequestBody StoreDto payload) {
		Store store1 = conversion.convertToStoreEntity(payload);
		storeService.getById(payload.getId());
		storeService.save(store1);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/updateStores", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<StoreDto> updateStores(@RequestBody List<StoreDto> storesDto) {
		List<Store> store = storesDto.stream().map(storeDto -> conversion.convertToStoreEntity(storeDto))
				.collect(Collectors.toList());
		List<Store> storeCreated = storeService.saveAll(store);
		return storeCreated.stream().map(st -> conversion.convertToStoreDto(st)).collect(Collectors.toList());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<StoreDto> getUser(@PathVariable("id") int id) {
		StoreDto storeDto = conversion.convertToStoreDto(storeService.getById(id));
		return new ResponseEntity<>(storeDto, HttpStatus.FOUND);

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<StoreDto> getAllStoresList() {
		List<Store> stores = storeService.findAll();
		List<StoreDto> storelist = stores.stream().map(store -> conversion.convertToStoreDto(store))
				.collect(Collectors.toList());
		return storelist;
	}

}
