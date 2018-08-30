package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long productId;
	
	private String productName;
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public Product(long productId, String productName, int price, int prodStoreId, int prodMerchantId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.prodStoreId = prodStoreId;
		this.prodMerchantId = prodMerchantId;
	}

	public Product() {
		super();
	}

	private int price;
	
	private int prodStoreId;
	
	private int prodMerchantId;
	
	

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
