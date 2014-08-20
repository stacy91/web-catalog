package com.helpers.globalexceptionconfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


/*@ControllerAdvice*/
public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleIntegrityViolation(HttpServletRequest req, Exception exception){
		ModelAndView mv = new ModelAndView();
		mv.addObject("message",exception.getMessage());
		mv.addObject("exceptionStackTrace",exception.getStackTrace());
		mv.addObject("url", req.getRequestURL());
		mv.setViewName("adminErrors/un");
		return mv;
	}
}
