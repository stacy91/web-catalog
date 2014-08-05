package com.controller.management;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  
import com.dao.BrandsDao;



@Controller  
@RequestMapping("/management") 
public class BrandsController{  
	
	@Autowired
	private BrandsDao brandsDao;
	
	@RequestMapping("/brands")  
    public ModelAndView showBrands() {  
    	   	
    System.out.println("from controller");  
    return new ModelAndView("brands", "brands", brandsDao.getAllBrandValues());  
    }  
}  