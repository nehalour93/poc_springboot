package com.example.POC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.POC.model.Store;


@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
	
	Store findByid(int storeId);

	List<Store> findAll();
	
	List<Store> findAllByid(List<Store> list);

	Store save(Store persisted);

	Store getByid(int id);
	

}
