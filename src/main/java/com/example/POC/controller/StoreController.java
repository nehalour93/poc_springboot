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
import com.example.POC.model.Store;
import com.example.POC.repository.StoreRepository;
import com.example.POC.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private UserRepository userRepository;

	public static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	@RequestMapping(value = "/store/", method = RequestMethod.PUT)
	public ResponseEntity<String> createStore(@RequestBody Store store) {
		String msg = null;
		int id = store.getId();
		int merchantId = store.getMerchantId();
		if (userRepository.findById(merchantId) != null) {
			if (storeRepository.findByid(id) != null) {
				msg = "Unable to create store with id " + id + " as it already exists";
				logger.error(msg);
				return new ResponseEntity<>(msg, HttpStatus.CONFLICT);

			} else {
				msg = "Store with id" + id + " created";
				logger.info("Creating Store : {}", store);
				storeRepository.save(store);

			}
		} else {
			msg = "Unable to create store for merchantId " + merchantId
					+ " since user with this merchantId does not exist.";
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/stores/", method = RequestMethod.PUT)
	public ResponseEntity<String> createStores(@RequestBody List<Store> stores) {
		StringBuilder sb = new StringBuilder("");
		for (Store s : stores) {
			int id = s.getId();
			int merchantId = s.getMerchantId();
			if (userRepository.findById(merchantId) != null) {
				if (storeRepository.findByid(id) != null) {
					sb.append("Unable to create store with id " + id + " as it already exists.");
					logger.error("Unable to create store");

				} else {
					sb.append("\nStore with id " + id + " created");
					logger.info("Creating Store : {}", s);
					storeRepository.save(s);

				}
			} else {
				sb.append("Unable to create store for merchantId " + merchantId
						+ " user with this merchantId does not exist.");
			}
		}

		return new ResponseEntity<>(sb.toString(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/store/", method = RequestMethod.POST)
	public ResponseEntity<String> updateStore(@RequestBody Store store) {
		String msg = null;
		int id = store.getId();
		if (storeRepository.findByid(id)== null) {
			msg = "Unable to update store with id " + id + " as it already exists.";
			logger.error(msg);
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		}

		else {
			msg = "Store with id" + id + " updated";
			logger.info("Updating Store : {}", store);
			storeRepository.save(store);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@RequestMapping(value = "/stores/", method = RequestMethod.POST)
	public ResponseEntity<String> updateStores(@RequestBody List<Store> stores) {
		StringBuilder sb = new StringBuilder("");
		for (Store s : stores) {
			int id = s.getId();
			if (storeRepository.findByid(id) == null) {
				sb.append("\nUnable to Update. A Store with id " + id + " doesn't exist.");
			} else {
				sb.append("\nStore with id " + id + " updated");
				logger.info("Updating Store : {}", s);
				storeRepository.save(s);
			}
		}
		return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/store/{id}", method = RequestMethod.GET)
	public ResponseEntity<Store> getStore(@PathVariable("id") int id) {
		String msg = null;
		Store store = storeRepository.findByid(id);
		if (store == null) {
			msg = "Unable to retrieve. A Store with id " + id + " doesn't exist.";
			logger.error(msg);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		else {
			logger.info("Retrieving Store : {}", id);
			storeRepository.getByid(id);
		}

		return new ResponseEntity<>(store, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/stores/", method = RequestMethod.GET)
	public ResponseEntity<List<Store>> getAllStores() {
		List<Store> stores = storeRepository.findAll();
		if (stores.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(stores, HttpStatus.OK);
	}

}
