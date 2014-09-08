package com.helpers.globalexceptionconfig;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;



@ControllerAdvice
public class GlobalControllerExceptionHandler {
	

	private final String title ="Error.title";
	private Logger log = Logger.getLogger("exceptionsLogger");

	private ModelAndView setModel(String msg) {
		ModelAndView model = new ModelAndView();
		model.addObject("title", title);
		model.addObject("msg", msg);
		model.setViewName("error");
		return model;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleDuplicateNameViolation(DataIntegrityViolationException ex){
			
		log.error("Data Integrity violation exception", ex);
		return setModel(
		"Error.dataIntegrityViolation");
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handleDuplicateNameViolation(ConstraintViolationException ex){
			
		log.error("Constraint violation exception", ex);
		return setModel(
		"Error.constraintViolation");
	}

}
