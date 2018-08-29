package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	private String name;
	
	@NotNull
	private String userType;
	
	private String accountStatus;
	

	public User() {
		super();
	}

	public User(int id, String name, String userType, String accountStatus) {
		super();
		this.id = id;
		this.name = name;
		this.userType = userType;
		this.accountStatus = accountStatus;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
        return id+" | " + name;
    }

}
