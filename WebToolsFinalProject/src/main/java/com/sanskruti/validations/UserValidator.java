package com.sanskruti.validations;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sanskruti.pojo.User;

public class UserValidator implements Validator {
	
	private final Pattern patternun = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})");
	private final Pattern patternpa = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User u =(User)target;
		if(!patternun.matcher(u.getUserName()).matches()){
            errors.rejectValue("userName", "error.userName.format","Enter the UserName in correct format !");
        }
		if(!patternpa.matcher(u.getPassword()).matches()){
            errors.rejectValue("password", "error.password.format","Enter a strong password !");
        }
		
	}

}
