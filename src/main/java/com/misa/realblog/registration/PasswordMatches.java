package com.misa.realblog.registration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// now on the type level annotation, because we need the entire userDTO object to perform the validation
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches {
	
	String message() default "Passwords don't match";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
