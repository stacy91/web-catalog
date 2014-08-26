package com.controller;

import java.io.IOException;

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
import com.helpers.FilteredCollection;
import com.entities.services.Orders_SalesService;
import com.entities.services.UsersService;
import com.helpers.DeviceHelper;


import java.security.Principal;

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
	@Autowired 
	private Orders_SalesService o_sService;
	
	@RequestMapping("*")
	public String welcome(ModelMap model, Integer page, Integer brandId, String search){
		
		FilteredCollection<Device> fDevices = devicesService.getDevices(page,brandId,search);

	    model.addAttribute("beginIndex", fDevices.getBegin());
	    model.addAttribute("endIndex", fDevices.getEnd());
	    model.addAttribute("currentIndex", fDevices.getCurrentPage());
		model.addAttribute("totalPages",fDevices.getTotalPages());
		model.addAttribute("brands", brandsService.getBrands());
		model.addAttribute("devices", fDevices.getItems());
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
	
	@RequestMapping(value="/order", method=RequestMethod.POST)
	public String Order(Integer deviceId,Integer brandId,String search,Principal principal,
			Integer amount,Integer page){
		
		o_sService.create(deviceId, principal.getName(), amount);
		String redirect = "redirect:";
		
		if(page != null)
			redirect += "page=" + page;
		if(brandId != null)
			redirect += "?brandId=" + brandId;
		if(search != null && !search.isEmpty())
			redirect += "?search=" + search;
		
		return redirect;
	}
}
