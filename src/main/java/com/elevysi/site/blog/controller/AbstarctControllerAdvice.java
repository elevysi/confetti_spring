package com.elevysi.site.blog.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AbstarctControllerAdvice {
	
private static final Logger logger = LoggerFactory.getLogger(AbstarctControllerAdvice.class);
	
//	@ExceptionHandler(SQLException.class)
//	public String handleSQLException(HttpServletRequest request, Exception ex){
//		logger.info("SQLException Occured:: URL="+request.getRequestURL());
//		return "database_error";
//	}
//	
//	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
//	@ExceptionHandler(IOException.class)
//	public void handleIOException(){
//		logger.error("IOException handler executed");
//		//returning 404 error code
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleAllException(Exception ex) {
//
//		ModelAndView model = new ModelAndView("error/exception_error");
//		model.addObject("exception", ex);
//		return model;
//
//	}
	

}
