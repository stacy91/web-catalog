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
import com.entities.User;
import com.entities.services.UsersService;
import com.helpers.DeviceHelper;

@Controller
@RequestMapping("/")
public class Home {
	

	@Autowired
	UsersService usersService;
	@Autowired
	DeviceHelper deviceHelper;
	
	@RequestMapping("*")
	public String welcome(){
		
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
