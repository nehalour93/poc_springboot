package com.example.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.model.Store;


@Service
public interface StoreService {

	List<Store> findAll();

	Store save(Store store);
	
	List<Store> saveAll(List<Store> stores);

	Store getById(long id);

	List<Store> updateAll(List<Store> stores);
	
	
}