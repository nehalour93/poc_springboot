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
import com.example.POC.model.Product;
import com.example.POC.model.Store;
import com.example.POC.repository.ProductRepository;
import com.example.POC.repository.StoreRepository;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StoreRepository storeRepository;

	public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@RequestMapping(value = "/product/", method = RequestMethod.PUT)
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		String msg = null;
		int id = product.getProductId();
		int storeId = product.getProdStoreId();
		if (storeRepository.findByid(storeId) == null) {
			msg = "Unable to create Product for storeId  " + storeId + " store with this storeId does not exist.";
			return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
		} else {
			if (productRepository.findByproductId(id) != null) {
				msg = "Unable to create. A Product with id " + id + " already exists.";
				logger.error(msg);
				return new ResponseEntity<>(msg, HttpStatus.CONFLICT);

			} else {
				msg = "Product created";
				logger.info("Creating Product : {}", product);
				Store store = storeRepository.findByid(storeId);
				product.setProdMerchantId(store.getMerchantId());
				productRepository.save(product);
			}
		}

		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/product/", method = RequestMethod.POST)
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		String msg = null;
		int id = product.getProductId();
		if (productRepository.findByproductId(id) == null) {
			msg = "Unable to Update. A Product with id " + id + " doesn't exist.";
			logger.error(msg);
			return new ResponseEntity<>(msg, HttpStatus.CONFLICT);

		} else {
			msg = "Product updated";
			logger.info("Updating Product : {}", product);
			productRepository.save(product);
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		String msg = null;
		Product product = null;
		product = productRepository.findByproductId(id);
		if (product == null) {
			msg = "Unable to retrieve. A Product with id " + id + " doesn't exist.";
			logger.error(msg);
			return new ResponseEntity<>(product,HttpStatus.NOT_FOUND);
		}

		else {
			logger.info("Retrieving Product : {}", id);
			productRepository.findByproductId(id);
		}

		return new ResponseEntity<>(product, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/products/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

}
