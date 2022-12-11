package com.example.backend.dto;

import java.io.Serializable;

import com.example.backend.model.User;

public class UserDeleteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	

	private UserDeleteDTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public final String getFirstName() {
		return firstName;
	}
	public final String getLastName() {
		return lastName;
	}
	
	public static UserDeleteDTO of(User user) {
		return new UserDeleteDTO(user.getFirstName(),user.getLastName());
		
	}
}
