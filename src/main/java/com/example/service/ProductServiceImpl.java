package com.example.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exception.CustomException;
import com.example.model.Product;
import com.example.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public Product getById(long id) {
		logger.info("inside getbyid " + productRepository.getOne(id));
		Product product = productRepository.getOne(id);
		if (product == null)
			throw new CustomException("product with id " + id + " does not exist");
		else {
			return product;
		}
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
		if (product.getProductName().equals("")) {
			throw new CustomException("name cannot be null");
		}
		return productRepository.save(product);
	}


}