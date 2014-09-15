package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helpers.FilteredCollection;
import com.entities.dto.DeviceDto;
import com.entities.dto.Order_SaleDto;
import com.entities.dto.UserDto;
import com.entities.services.BrandsService;
import com.entities.services.DevicesService;
import com.entities.services.Orders_SalesService;
import com.entities.services.UsersService;
import com.helpers.DeviceHelper;
import com.helpers.FilteredCollectionGenerator;
import com.helpers.MyUserDetails;

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
	public String welcome(ModelMap model, Integer page,
			Integer brandId, String search, String login_error){
		
		FilteredCollection<DeviceDto> fDevices = devicesService.getFiltered(devicesService.getAll(brandId,search), page);

	    FilteredCollectionGenerator.fillPagination(model, fDevices);
	    
		model.addAttribute("brands", brandsService.getAll());
		model.addAttribute("devices", fDevices.getItems());
		model.addAttribute("brandId",brandId);
		model.addAttribute("search",search);
		model.addAttribute("page", page);
		if(login_error != null && login_error.equals("t"))
			model.addAttribute("errorMsg", "Error.badCreditentials");
		return "home";
	}
	
	@RequestMapping("/register")
	public String register(ModelMap model){
		
		model.addAttribute("user", new UserDto());
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") @Valid UserDto user,
			BindingResult result,HttpServletRequest request, String action)
			{
		
		if (!action.equals("cancel")) {
			UserDto test = usersService.find(user.getLogin());
			if (test != null)
				result.reject("Unique.user.login");

			if (result.hasErrors())
				return "register";

			if (usersService.create(user) != null) {

				Authentication auth = new UsernamePasswordAuthenticationToken(
						new MyUserDetails(user), user.getPassword());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		return "redirect:/home";
	}
	
	@RequestMapping("/getImage")
	@ResponseBody
	public byte[] getImage(String id, HttpServletRequest request) throws IOException{
		
		return deviceHelper.getImgBytes(id,request);
	}
	
	@RequestMapping(value="/order", method=RequestMethod.POST)
	public String Order(@RequestParam("deviceId") Integer deviceId,Integer brandId,String search,@RequestParam("login") String login,
			@RequestParam("amount") Integer amount,Integer page)
					{
		
		Order_SaleDto o_s = o_sService.initOrder_Sale(deviceId, login, amount);
		if(o_s != null)
			o_sService.create(o_s);
		
		String redirect = "redirect:?";
		
		
		if(brandId != null)
			redirect += "brandId=" + brandId + "&";
		if(search != null && !search.isEmpty())
			redirect += "search=" + search + "&";
		if(page != null)
			redirect += "page=" + page;
		
		return "notificateOrdered";
	}
}
