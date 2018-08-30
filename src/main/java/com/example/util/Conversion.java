package com.example.util;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.dto.ProductDto;
import com.example.dto.StoreDto;
import com.example.dto.UserDto;
import com.example.exception.CustomException;
import com.example.model.Product;
import com.example.model.Store;
import com.example.model.User;
import org.slf4j.Logger;

@Component
public class Conversion {
	
	@Autowired
    private  ModelMapper modelMapper;
	
	public static final Logger logger = LoggerFactory.getLogger(Conversion.class);
	
	public UserDto convertToUserDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setUserType(user.getAccountStatus());
		userDto.setAccountStatus(user.getAccountStatus());
		return userDto;
	}

	public User convertToUserEntity(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setUserType(userDto.getUserType());
		user.setAccountStatus(userDto.getAccountStatus());
		return user;
	}
	
	public StoreDto convertToStoreDto(Store store) {
		if(store.getStoreName().equals("")){
			throw new CustomException("name cannot be null");
		}
		StoreDto storeDto = modelMapper.map(store, StoreDto.class);
		storeDto.setId(store.getId());
		storeDto.setStoreName(store.getStoreName());
		return storeDto;
	}

	public Store convertToStoreEntity(StoreDto storeDto) {
		Store store = modelMapper.map(storeDto, Store.class);
		store.setId(storeDto.getId());
		store.setStoreName(storeDto.getStoreName());
		return store;
	}
	
	public ProductDto convertToProductDto(Product product) {
		if(product.getProductName().equals("")){
			throw new CustomException("name cannot be null");
		}
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		productDto.setProductId(product.getProductId());
		productDto.setProductName(product.getProductName());
		return productDto;
	}

	public Product convertToProductEntity(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		product.setProductId(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setPrice(productDto.getPrice());
		return product;
	}

}
