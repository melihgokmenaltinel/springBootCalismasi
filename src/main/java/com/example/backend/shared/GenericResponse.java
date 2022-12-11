package com.example.backend.shared;

import java.time.LocalDate;
import java.util.Date;

//shared yada util olabilir paket ismi
//işlem başarılı olduğunda kullanıcıya döneceğimiz mesajın classı
public final class GenericResponse {

	private String message;

	public GenericResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
