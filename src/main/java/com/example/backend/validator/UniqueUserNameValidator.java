package com.example.backend.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.backend.repository.UserRepository;

public final class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

	private final UserRepository userRepository;
	
	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
 		return !userRepository.existsUserByUsername(userName);
	}

	public UniqueUserNameValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
