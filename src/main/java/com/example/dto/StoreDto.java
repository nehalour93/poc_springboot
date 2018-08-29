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
	private int id;

	private String storeName;

	private int merchantId;

	public StoreDto(int id, String storeName, int merchantId, Set<Payment> payments) {
		super();
		this.id = id;
		this.storeName = storeName;
		this.merchantId = merchantId;
		this.payments = payments;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "store_payment", joinColumns = @JoinColumn(name = "storeId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "paymentId", referencedColumnName = "id"))
	private Set<Payment> payments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

}
