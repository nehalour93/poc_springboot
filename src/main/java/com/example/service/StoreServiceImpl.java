package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.CustomException;
import com.example.model.Store;
import com.example.model.User;
import com.example.repository.StoreRepository;
import com.example.repository.UserRepository;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private UserService userService;

	public static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

	@Override
	public Store getById(long id) {
		logger.info("inside getbyid " + storeRepository.getOne(id));
		Store store = storeRepository.getOne(id);
		if (store == null)
			throw new CustomException("No store with id " + id + " found");
		else {
			return store;
		}
	}

	@Override
	public List<Store> findAll() {
		List<Store> stores = storeRepository.findAll();
		if (stores.isEmpty()) {
			throw new CustomException("store not found");
		}
		return stores;
	}

	@Override
	public Store save(Store store) {
		if (store.getStoreName().equals("")) {
			throw new CustomException("name cannot be null");
		}
		return storeRepository.save(store);
	}

	@Override
	public List<Store> updateAll(List<Store> stores) {
		List<Store> storeUpdated = new ArrayList<>();
		for (Store s : stores) {
			if (storeRepository.getOne(s.getId()) != null)
				storeUpdated.add(s);
			else {
				throw new CustomException(
						"Unable to update store for Id " + s.getId() + " since store with this Id does not exist.");
			}

		}
		return storeRepository.saveAll(storeUpdated);
	}

	@Override
	public List<Store> saveAll(List<Store> stores) {
		List<Store> storeCreated = new ArrayList<>();
		for (Store s : stores) {
			if (userService.getById(s.getMerchantId()) != null)
				storeCreated.add(s);
			else {
				throw new CustomException("Unable to create store for merchantId " + s.getMerchantId()
						+ " since user with this merchantId does not exist.");
			}

		}
		return storeRepository.saveAll(storeCreated);
	}

}
