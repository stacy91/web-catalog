package com.helpers.globalexceptionconfig;
import org.springframework.security.core.AuthenticationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;


@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	/*@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleIntegrityViolation(HttpServletRequest req, Exception exception){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message",exception.getMessage());
		mv.addObject("exceptionStackTrace",exception.getStackTrace());
		mv.addObject("url", req.getRequestURL());
		mv.setViewName("adminErrors/un");
		return mv;
	}*/
	private final String title ="Error.title";
	private Logger log = Logger.getLogger("exceptionsLogger");

	
	private ModelAndView setModel(String msg) {
		ModelAndView model = new ModelAndView();
		model.addObject("title", title);
		model.addObject("msg", msg);
		model.setViewName("error");
		return model;
	}
	
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handleDuplicateNameViolation(ConstraintViolationException ex){
		
		log.error("Duplicate name", ex);
		return setModel(
		"Error.duplicateName");
	}

	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleDuplicateNameViolation(DataIntegrityViolationException ex){
		
		log.error("Delete Cascaded", ex);
		return setModel(
				"Error.deleteWithRef");
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ModelAndView handleDuplicateNameViolation(AuthenticationException ex){
		
		log.error("Authentication problems", ex);
		return setModel(
				"Error.deleteWithRef");
	}
}
