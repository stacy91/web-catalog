package com.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.Device;
import com.entities.User;
import com.entities.services.BrandsService;
import com.entities.services.DevicesService;
import com.entities.services.UsersService;
import com.helpers.DeviceHelper;
import com.helpers.FilteredDevices;

@Controller
@RequestMapping("/")
public class Home {
	

	@Autowired
	UsersService usersService;
	@Autowired
	DeviceHelper deviceHelper;
	@Autowired
	BrandsService brandsService;
	@Autowired
	DevicesService devicesService;
	
	@RequestMapping("*")
	public String welcome(ModelMap model, Integer page, Integer brandId, String search){
		
		FilteredDevices fDevices = devicesService.getDevices(page,brandId,search);

	    model.addAttribute("beginIndex", fDevices.getBegin());
	    model.addAttribute("endIndex", fDevices.getEnd());
	    model.addAttribute("currentIndex", fDevices.getCurrentPage());
		model.addAttribute("totalPages",fDevices.getTotalPages());
		model.addAttribute("brands", brandsService.getBrands());
		model.addAttribute("devices", fDevices.getDevices());
		model.addAttribute("brandId",brandId);
		model.addAttribute("search",search);
		return "home";
	}
	
	@RequestMapping("/register")
	public String register(ModelMap model){
		
		model.addAttribute("user", new User());
		return "register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(@Valid User user,String confirmPas, BindingResult result){
		
		if(!result.hasErrors() && user.getPassword().equals(confirmPas)){
			usersService.create(user);
		}
		return "redirect:/home";
	}
	
	@RequestMapping("/getImage")
	@ResponseBody
	public byte[] getImage(String id) throws IOException{
		
		return deviceHelper.getImgBytes(id);
	}
}
