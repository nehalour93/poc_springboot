package com.example.dto;

import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.example.model.Store;

public class PaymentDto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String paymentType;

	@ManyToMany(mappedBy = "payments")
	private Set<Store> stores;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PaymentDto(int id, String paymentType, Set<Store> stores) {
		super();
		this.id = id;
		this.paymentType = paymentType;
		this.stores = stores;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Set<Store> getStores() {
		return stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}

}
