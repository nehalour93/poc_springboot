package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.CustomException;
import com.example.model.Product;
import com.example.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getById(int id) {
		if (productRepository.findById(id) != null)
			throw new CustomException("product with id " + id + " does not exist");
		return productRepository.getOne(id);
	}

	@Override
	public List<Product> findAll() {
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new CustomException("product not found");
		}
		return products;
	}

	@Override
	public Product save(Product product) {
		if (product.getProductName() == "") {
			throw new CustomException("name cannot be null");
		}
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		return productRepository.save(product);
	}

}