package com.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class Home {
	
	@RequestMapping("*")
	public String welcome(Principal user){
		return "home";
	}
}
