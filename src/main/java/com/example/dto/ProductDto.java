package com.example.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductDto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long productId;

	private String productName;

	private int price;

	public ProductDto() {
		super();
	}

	private int prodStoreId;

	private int prodMerchantId;



	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
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

	public void setPrice(int price) {
		this.price = price;
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

	

	public ProductDto(long productId, String productName, int price, int prodStoreId, int prodMerchantId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.prodStoreId = prodStoreId;
		this.prodMerchantId = prodMerchantId;
	}

	public void setProdMerchantId(int prodMerchantId) {
		this.prodMerchantId = prodMerchantId;
	}

}
