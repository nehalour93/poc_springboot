package com.example.dto;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.example.model.Payment;

public class StoreDto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	public StoreDto(long id, String storeName, long merchantId, Set<Payment> payments) {
		super();
		this.id = id;
		this.storeName = storeName;
		this.merchantId = merchantId;
		this.payments = payments;
	}

	public StoreDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	private String storeName;

	private long merchantId;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "store_payment", joinColumns = @JoinColumn(name = "storeId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "paymentId", referencedColumnName = "id"))
	private Set<Payment> payments;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

}
