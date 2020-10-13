package com.misa.realblog.registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + 
												"[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return (validateEmail(value));
	}
	
	private boolean validateEmail(String value) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	

}
