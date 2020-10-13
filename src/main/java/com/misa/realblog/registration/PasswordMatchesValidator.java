package com.misa.realblog.registration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>{

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		UserDTO user = (UserDTO) value;
		return user.getPassword().equals(user.getMatchingPasswords());
	}
}
