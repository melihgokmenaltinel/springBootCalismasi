package com.example.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.backend.validator.UniqueUserName;

public class UserCreateDTO {

	// pek çok dilde kullanılabileceği için mesaj propertiesleri resources
	// içerisinde ayrı file içerisinde yazdık
	@NotNull(message = "{backend.constraints.firstName.NotNull.message}")
	@Size(min = 2, max = 32, message = "{backend.constraints.firstName.Size.message}")
	private String firstName;

	@NotNull(message = "{backend.constraints.lastName.NotNull.message}")
	@Size(min = 3, max = 32, message = "{backend.constraints.lastName.Size.message}")
	private String lastName;

	@NotNull(message = "{backend.constraints.userName.NotNull.message}")
	@Size(min = 4, max = 24, message = "{backend.constraints.userName.Size.message}")
	@UniqueUserName()
	private String userName;
	
	public UserCreateDTO() {
	}

	public UserCreateDTO(String firstName, String lastName ,  String userName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
