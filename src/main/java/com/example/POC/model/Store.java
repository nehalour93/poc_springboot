package com.example.POC.model;

import java.util.Set;
import javax.persistence.*;

@Entity
public class Store {

	@Id
	@GeneratedValue
	private int id;

	private String storeName;

	private int merchantId;

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
