package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.Device;
import com.entities.User;
import com.helpers.FilteredCollection;
import com.entities.servicesImpl.BrandsService;
import com.entities.servicesImpl.DevicesService;
import com.entities.servicesImpl.Orders_SalesService;
import com.entities.servicesImpl.UsersService;
import com.helpers.DeviceHelper;









import com.helpers.FilteredCollectionGenerator;

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
	@Autowired
    protected AuthenticationManager authenticationManager;
	
	

	
	@RequestMapping("*")
	public String welcome(ModelMap model, Integer page, Integer brandId, String search){
		
		FilteredCollection<Device> fDevices = devicesService.getDevices(page,brandId,search);

	    FilteredCollectionGenerator.fillPagination(model, fDevices);
	    
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
	public String register(@Valid User user,String confirmPas, BindingResult result,HttpServletRequest request){
		
		if(!result.hasErrors() && user.getPassword().equals(confirmPas)){
			if(usersService.create(user) != null){
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
			            user.getLogin(), user.getPassword());
				token.setDetails(new WebAuthenticationDetails(request));
				Authentication authenticatedUser = authenticationManager.authenticate(token);
				SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			}
				
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
		
		
		if(brandId != null)
			redirect += "?brandId=" + brandId;
		if(search != null && !search.isEmpty())
			redirect += "&search=" + search;
		if(page != null)
			redirect += "&page=" + page;
		
		return redirect;
	}
}
