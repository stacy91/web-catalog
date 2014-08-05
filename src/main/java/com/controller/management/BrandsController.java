package com.controller.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  

import com.dao.BrandsDao;
import com.entities.Brand;



@Controller  
@RequestMapping("/management") 
public class BrandsController{  
	
	private BrandsDao brandsDao;
	
	@Autowired
	public BrandsController(BrandsDao brandsDao) {
		this.brandsDao = brandsDao;
	}

	@RequestMapping("/brands")  
    public ModelAndView showBrands() {  
    	   	
    System.out.println("from controller");  
    List<Brand> brands = brandsDao.getAllBrandValues();
    
    return new ModelAndView("brands", "brands", brands);  
    }  
}  