package com.controller.management;



import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value="")
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
	public String addDevice(@Valid Device device,MultipartFile image,
			String action, BindingResult result,HttpServletRequest request){
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			String path = servletContext.getRealPath("/");
			device.setImageURL(path);
			devicesDao.create(device,image);
		}
		return "redirect:/management/devices";
	}
	
	@RequestMapping(value="/updateDevice")
	public String updateDevice(int id,ModelMap model){
		model.addAttribute("device", devicesDao.findById(id));
		model.addAttribute("brands", brandsDao.getAllBrandValues());
		return "adminDevices/update";
	}
	@RequestMapping(value="/updateDevice", method=RequestMethod.POST)
	public String updateDevice(@Valid Device device,
			String action, BindingResult result){
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			devicesDao.update(device);
		}
		return "redirect:/management/devices";
	}
	
	@RequestMapping(value="/deleteDevice",method=RequestMethod.POST)
	public String updateDevice(int id){
		Device device = devicesDao.findById(id);
		device.setImageURL(servletContext.getRealPath("/") + device.getImageURL());
		devicesDao.delete(device);
		return "redirect:/management/devices";
	}
}  