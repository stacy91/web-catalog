package com.controller.management;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.BrandsDao;
import com.dao.DevicesDao;
import com.entities.Brand;
import com.entities.Device;

@Controller 
@RequestMapping(value="/management")
public class ManagementController{  
	
	@Autowired
	private BrandsDao brandsDao;
	@Autowired
	private DevicesDao devicesDao;
	
	@RequestMapping(value="/") 
	public String index(){
		return "adminIndex";
	}
	
	//Brands
	
	@RequestMapping(value="/brands")  
    public String showBrands(ModelMap model) {  
	List<Brand> brands = brandsDao.getAllBrandValues();
    model.addAttribute("brands", brands);
    return "adminBrands";
    }  
	
	@RequestMapping(value="/addBrand")  
    public String addBrand(ModelMap model) {  
    model.addAttribute("brand", new Brand());
    return "adminBrands/add";
    }  
	@RequestMapping(value="/addBrand", method=RequestMethod.POST)  
    public String addBrand(@RequestParam String action, @Valid Brand brand,BindingResult result) {  
	if(!action.equals("cancel"))
	{
		if(result.hasErrors())
			return "adminBrands/add";
		brandsDao.create(brand);
		
	}
	return "redirect:/management/brands";		
	} 
	
	
	@RequestMapping(value="/updateBrand")
	public String updateBrand(int id, ModelMap model){
		Brand brandToUpdate = brandsDao.findById(id);
		if(brandToUpdate == null)
			return "redirect:/management/brands";
		model.addAttribute("brand", brandToUpdate);
		return "adminBrands/update";
	}
	@RequestMapping(value="/updateBrand",method=RequestMethod.POST)
	public String updateBrand(@RequestParam String action,@Valid Brand brand, BindingResult result){
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			brandsDao.update(brand);
			
		}
		return "redirect:/management/brands";
	}
	
	
	@RequestMapping(value="/deleteBrand", method=RequestMethod.POST)	
	public String deleteBrand(int id){
		brandsDao.delete(id);
		return "redirect:/management/brands";
	}
	
	//end Brands
	
	//Devices
	
	@RequestMapping(value="/devices")  
    public String showDevices(ModelMap model) {  
	List<Device> devices = devicesDao.getAllDeviceValues();
    model.addAttribute("devices", devices);
    return "adminDevices";
    }
	
	@RequestMapping(value="/addDevice")
	public String addDevice(ModelMap model){
		model.addAttribute("device", new Device());
		model.addAttribute("brands", brandsDao.getAllBrandValues());
		return "adminDevices/add";
	}
	
	@RequestMapping(value="/addDevice", method=RequestMethod.POST)
	public String addDevice(@RequestParam("device") Device device, String action){
		
		
		return null;
	}
		 
}  