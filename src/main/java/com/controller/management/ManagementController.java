package com.controller.management;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.entities.Arrival;
import com.entities.Brand;
import com.entities.Device;
import com.entities.services.ArrivalsService;
import com.entities.services.BrandsService;
import com.entities.services.DevicesService;

@Controller 
@RequestMapping(value="/management")
public class ManagementController{  
	
	@Autowired
	private DevicesService devicesService;
	@Autowired
	private BrandsService brandsService;
	@Autowired
	private ArrivalsService arrivalsService;
	
	@RequestMapping(value="")
	public String index(){
		return "adminIndex";
	}
	
	//Brands
	
	@RequestMapping(value="/brands")  
    public String showBrands(ModelMap model) {  
	List<Brand> brands = brandsService.getBrands();
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
		brandsService.create(brand);	
	}
	return "redirect:/management/brands";		
	} 
	
	@RequestMapping(value="/updateBrand")
	public String updateBrand(int id, ModelMap model){
		Brand brandToUpdate = brandsService.findById(id);
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
			brandsService.update(brand);	
		}
		return "redirect:/management/brands";
	}
	
	
	@RequestMapping(value="/deleteBrand", method=RequestMethod.POST)	
	public String deleteBrand(int id){
		brandsService.delete(id);
		return "redirect:/management/brands";
	}
	
	//end Brands
	
	//Devices
	
	@RequestMapping(value="/devices")  
    public String showDevices(ModelMap model) {  
	List<Device> devices = devicesService.getDevices();
    model.addAttribute("devices", devices);
    model.addAttribute("brands", brandsService.getBrands());
    return "adminDevices";
    }   
	
	@RequestMapping(value="/addDevice")
	public String addDevice(ModelMap model){
		model.addAttribute("device", new Device());
		model.addAttribute("brands", brandsService.getBrands());
		return "adminDevices/add";
	}
	@RequestMapping(value="/addDevice", method=RequestMethod.POST)
	public String addDevice(@Valid Device device,MultipartFile image,
			String action, BindingResult result){
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			devicesService.create(device, image);
		}
		return "redirect:/management/devices";
	}
	
	@RequestMapping(value="/updateDevice")
	public String updateDevice(int id,ModelMap model){
		Device device = devicesService.getDevicesWithBrand(id);
		model.addAttribute("device", device);
		model.addAttribute("brands", brandsService.getBrands());
		return "adminDevices/update";
	}
	
	
	@RequestMapping(value="/updateDevice", method=RequestMethod.POST)
	public String updateDevice(@Valid Device device,MultipartFile image,
			String action, BindingResult result){
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			devicesService.update(device, image);
		}
		return "redirect:/management/devices";
	}
	
	@RequestMapping(value="/deleteDevice",method=RequestMethod.POST)
	public String deleteDevice(int id){
		devicesService.delete(id);
		return "redirect:/management/devices";
	}
	//end devices
	
	
	//Arrivals
	
	@RequestMapping(value="/arrivals")  
    public String showArrival(ModelMap model) {  
		
		model.addAttribute("arrivals", arrivalsService.getArrivals());
		return  "adminArrivals";
    }   
	
	@RequestMapping(value="/addArrival", method=RequestMethod.GET)
	public String addArrival(ModelMap model,int deviceId,Principal pricipal){
		
		model.addAttribute("arrival",arrivalsService.getArrivalToCreate(deviceId,pricipal.getName()));
		return "adminArrivals/add";
	}
	@RequestMapping(value="/addArrival", method=RequestMethod.POST)
	public String addArrival(@Valid Arrival arrival,int deviceId,int userId,
			String action, BindingResult result){

		if(!action.equals("cancel") && !result.hasErrors()){
				arrivalsService.create(arrival,deviceId,userId);
		}
		return "redirect:/management/devices";
	}
	
	@RequestMapping(value="/updateArrival")
	public String updateArrival(int id,ModelMap model){
		
		model.addAttribute("arrival", arrivalsService.getArrival(id));
		return "adminArrivals/update";
	}
	
	
	@RequestMapping(value="/updateArrival", method=RequestMethod.POST)
	public String updateArrival(@Valid Arrival arrival,int userId,Principal principal,
			String action, BindingResult result){

		if(!action.equals("cancel") && !result.hasErrors()){
			arrivalsService.update(arrival,principal.getName());
		}
		return "redirect:/management/arrivals";
	}
	
	@RequestMapping(value="/deleteArrival",method=RequestMethod.POST)
	public String deleteArrival(int id){
		
		arrivalsService.delete(id);
		return "redirect:/management/arrivals";
	}
	
	//end arrivals
}  