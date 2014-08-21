package com.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.User;
import com.entities.Dao.UserRolesDao;
import com.entities.Dao.UsersDao;

@Controller
@RequestMapping("/")
public class Home {
	
	@Autowired
	UsersDao usersDao;
	@Autowired
	UserRolesDao rolesDao;
	
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
			String encodedPas = user.getPassword();
			encodedPas = new BCryptPasswordEncoder().encode(encodedPas);
			user.setPassword(encodedPas);
			user.setRole(rolesDao.findById(4));
			usersDao.create(user);
		}
		return "redirect:/home";
	}
	
	@RequestMapping("/getImage")
	@ResponseBody
	public byte[] getImage(String path) throws IOException{
		BufferedImage bImage = ImageIO.read(new File(path));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", baos);
		return baos.toByteArray();
	}
}
