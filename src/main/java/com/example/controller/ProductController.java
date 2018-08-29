package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.ProductDto;
import com.example.exception.CustomException;
import com.example.model.Product;
import com.example.service.ProductService;
import com.example.service.StoreService;
import com.example.util.Conversion;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private Conversion conversion;

	public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ProductDto createProduct(@RequestBody ProductDto productDto) {
		Product  productCreated = null;
		Product product = conversion.convertToProductEntity(productDto);
		if (storeService.getById(product.getProdStoreId()) != null)
			productCreated = productService.save(product);
		else {
			throw new CustomException("Unable to create product for storeId " + product.getProdStoreId()
					+ " since store with this storeId does not exist.");
		}
		return conversion.convertToProductDto(productCreated);
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") int id) {
		Product productToBeUpdated = productService.getById(id);
		if (productToBeUpdated != null) {
			Product product = conversion.convertToProductEntity(productDto);
			productService.update(product);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ProductDto getProduct(@PathVariable("id") int id) {
		return conversion.convertToProductDto(productService.getById(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductDto> getAllProducts() {
		List<Product> products = productService.findAll();
		return products.stream().map(product -> conversion.convertToProductDto(product)).collect(Collectors.toList());
	}

}
