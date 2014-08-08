package com.controller.management;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  
import com.dao.BrandsDao;

@Controller  
@RequestMapping("/management") 
public class ManagementController{  
	
	@Autowired
	private BrandsDao brandsDao;
	
	@RequestMapping(value="") 
	public String index(){
		return "adminIndex";
	}
	
	@RequestMapping(value="/brands")  
    public ModelAndView showBrands() {  
    	   	
    System.out.println("from controller");  
    return new ModelAndView("adminBrands", "brands", brandsDao.getAllBrandValues());  
    }  
}  