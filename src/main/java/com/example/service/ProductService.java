package com.example.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.model.Product;

@Service
public interface ProductService {

	List<Product> findAll();

	Product save(Product product);

	Product getById(int id);
	
	Product update(Product product);
	
	
}
