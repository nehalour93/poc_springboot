package com.example.model;

import java.util.Set;
import javax.persistence.*;

@Entity
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	private String storeName;

	public Store(long id, String storeName, long merchantId, Set<Payment> payments) {
		super();
		this.id = id;
		this.storeName = storeName;
		this.merchantId = merchantId;
		this.payments = payments;
	}

	public Store() {
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
