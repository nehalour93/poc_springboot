package com.example.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@NotNull
	@NotEmpty
	private String name;

	private String userType;

	private String accountStatus;

	public UserDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public UserDto(long id, @NotNull @NotEmpty String name, String userType, String accountStatus) {
		super();
		this.id = id;
		this.name = name;
		this.userType = userType;
		this.accountStatus = accountStatus;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

}
