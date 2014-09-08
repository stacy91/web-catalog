package com.controller;


import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Error {

	private Logger log = Logger.getLogger("exceptionsLogger");
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(Principal principal,ModelMap model,HttpServletRequest request) {
 
		if (principal != null) {
			
			String message = "Access denied for username: " + principal.getName() + "\n";
			log.error(message);
		}
		model.addAttribute("title", "Error.title");
		model.addAttribute("msg", "Error.accessDenied");
		
		return "error";
 
	}
}
