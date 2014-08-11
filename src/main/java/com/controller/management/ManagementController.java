package com.controller.management;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.BrandsDao;
import com.entities.Brand;

@Controller  
@RequestMapping("/management") 
public class ManagementController{  
	
	@Autowired
	private BrandsDao brandsDao;
	
	@RequestMapping(value="") 
	public String index(){
		return "adminIndex";
	}
	
	//Brands
	
	@RequestMapping(value="/brands")  
    public String showBrands(ModelMap model) {  
	ArrayList<Brand> brands = new ArrayList<Brand>(brandsDao.getAllBrandValues());
    model.addAttribute("brands", brands);
    return "adminBrands";
    }  
	
	@RequestMapping(value="/addBrand")  
    public String addBrand(ModelMap model) {  
    model.addAttribute("brand", new Brand());
    return "adminBrands/add";
    }  
	
	@RequestMapping(value="/addBrand", method=RequestMethod.POST)  
    public String addBrand(Brand brand) {  
	brandsDao.create(brand);
    return "redirect:management/brands";
    } 
	
	@RequestMapping(value="/deleteBrand", method=RequestMethod.POST)	
	public String deleteBrand(int id){
		brandsDao.delete(id);
		return "redirect:management/brands";
	}
	
	//end Brands
	
	 
}  