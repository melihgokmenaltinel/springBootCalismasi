package com.example.backend.dto;

import java.io.Serializable;

import com.example.backend.model.User;

//Kullanıcı tarafına gösterilmemesini istediğimiz veriler olduğunda ara katman olarak kullanırız sadece gösterilecek şeyleri yazarıs
public final class UserViewDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	

	private UserViewDTO(String firstName, String lastName) {
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
	
	public static UserViewDTO of(User user) {
		return new UserViewDTO(user.getFirstName(),user.getLastName());
		
	}
}
