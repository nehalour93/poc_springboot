package com.example.POC.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.POC.model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Product findByproductId(int id);
	
	Product findByprodStoreId(int id);
	
	List<Product> findAllByprodStoreId(int prodStoreId);

	List<Product> findAll();

	Product save(Product persisted);

	Product getByproductId(int id);
	
	

}
