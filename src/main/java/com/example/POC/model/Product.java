package com.example.POC.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private int productId;
	
	private String productName;
	
	private int price;
	
	private int prodStoreId;
	
	private int prodMerchantId;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int productPrice) {
		this.price = productPrice;
	}

	public int getProdStoreId() {
		return prodStoreId;
	}

	public void setProdStoreId(int prodStoreId) {
		this.prodStoreId = prodStoreId;
	}

	public int getProdMerchantId() {
		return prodMerchantId;
	}

	public void setProdMerchantId(int prodMerchantId) {
		this.prodMerchantId = prodMerchantId;
	}
	
}
