package com.SaltOverflow.SpringUserWebsite.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.SaltOverflow.SpringUserWebsite.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == User.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Error.Empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Error.Empty");
	}

}
