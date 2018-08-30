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
	private long id;

	@NotNull
	private String name;
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public User(long id, @NotNull String name, @NotNull String userType, String accountStatus) {
		super();
		this.id = id;
		this.name = name;
		this.userType = userType;
		this.accountStatus = accountStatus;
	}

	@NotNull
	private String userType;
	
	private String accountStatus;
	

	public User() {
		super();
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
