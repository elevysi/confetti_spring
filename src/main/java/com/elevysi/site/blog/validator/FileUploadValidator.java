package com.elevysi.site.blog.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.elevysi.site.blog.form.FileUpload;

@Component
public class FileUploadValidator implements Validator{
	
	@Value("${valid.FileUpload.file}")
	private String file_error;
	
	public boolean supports(Class clazz) {
        return FileUpload.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object target, Errors errors)
    {
    	FileUpload file = (FileUpload) target;
    	if (file.getFile().getSize() == 0) {
    		errors.rejectValue("file", file_error);
    	}

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.firstName", "First name is required.");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.lastName", "Last name is required.");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.email", "Email is required.");
    }
	
}
