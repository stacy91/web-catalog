package com.controller.management;

import java.util.List;

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

import com.dao.ArrivalsDao;
import com.dao.BrandsDao;
import com.dao.DevicesDao;
import com.dao.UserRolesDao;
import com.dao.UsersDao;
import com.entities.Arrival;
import com.entities.Brand;
import com.entities.Device;
import com.entities.User;
import com.entities.UserRole;
import com.helpers.DeviceHelper;

@Controller 
@RequestMapping(value="/management")
public class ManagementController{  
	
	@Autowired
	private DeviceHelper deviceHelper;
	@Autowired
	private BrandsDao brandsDao;
	@Autowired
	private DevicesDao devicesDao;
	@Autowired
	private ArrivalsDao arrivalsDao;
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private UserRolesDao userRolesDao;
	
	
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
    model.addAttribute("brands", brandsDao.getAllBrandValues());
    model.addAttribute("imgsPath",deviceHelper.getImgsPath());
    return "adminDevices";
    }   
	
	@RequestMapping(value="/addDevice")
	public String addDevice(ModelMap model){
		model.addAttribute("device", new Device());
		model.addAttribute("brands", brandsDao.getAllBrandValues());
		model.addAttribute("imgsPath",deviceHelper.getImgsPath());
		return "adminDevices/add";
	}
	@RequestMapping(value="/addDevice", method=RequestMethod.POST)
	public String addDevice(@Valid Device device,MultipartFile image,
			String action, BindingResult result){
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			devicesDao.create(device);
			if(!image.isEmpty() && deviceHelper.validateImage(image))
			{
				if(deviceHelper.saveImage(device.getId(), image)){
					device.setHasImage(true);
					devicesDao.update(device);
				}
			}
		}
		return "redirect:/management/devices";
	}
	
	@RequestMapping(value="/updateDevice")
	public String updateDevice(int id,ModelMap model){
		Device device = devicesDao.initBrand(id);
		model.addAttribute("device", device);
		model.addAttribute("brands", brandsDao.getAllBrandValues());
		model.addAttribute("imgsPath",deviceHelper.getImgsPath());
		return "adminDevices/update";
	}
	
	
	@RequestMapping(value="/updateDevice", method=RequestMethod.POST)
	public String updateDevice(@Valid Device device,MultipartFile image,
			String action, BindingResult result){
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			if(!image.isEmpty() && deviceHelper.validateImage(image))
			{
				if(deviceHelper.saveImage(device.getId(), image))
					device.setHasImage(true);
			}
			devicesDao.update(device);
		}
		return "redirect:/management/devices";
	}
	
	@RequestMapping(value="/deleteDevice",method=RequestMethod.POST)
	public String deleteDevice(int id){
		Device device = devicesDao.findById(id);
		deviceHelper.deleteImage(device.getId());
		devicesDao.delete(device);
		return "redirect:/management/devices";
	}
	//end devices
	
	
	//Arrivals
	
	@RequestMapping(value="/arrivals")  
    public String showArrival(ModelMap model) {  
		model.addAttribute("arrivals", arrivalsDao.getAllArrivalValues());
		return  "adminArrivals";
    }   
	
	@RequestMapping(value="/addArrival", method=RequestMethod.GET)
	public String addArrival(ModelMap model,int deviceId){
		Device device = devicesDao.initBrand(deviceId);
		User tempUser = usersDao.initRole(2);
		Arrival arrival = new Arrival();
		arrival.setDevice(device);
		arrival.setUser(tempUser);
		model.addAttribute("arrival",arrival);
		return "adminArrivals/add";
	}
	@RequestMapping(value="/addArrival", method=RequestMethod.POST)
	public String addArrival(@Valid Arrival arrival,int deviceId,int userId,
			String action, BindingResult result){
		
		arrival.setDevice(devicesDao.initBrand(deviceId));
		arrival.setUser(usersDao.initRole(userId));
		if(!action.equals("cancel") && !result.hasErrors()){
				arrivalsDao.create(arrival);
		}
		return "redirect:/management/devices";
	}
	
	@RequestMapping(value="/updateArrival")
	public String updateArrival(int id,ModelMap model){
		Arrival arrival = arrivalsDao.initProxy(id);
		model.addAttribute("arrival", arrival);
		return "adminArrivals/update";
	}
	
	
	@RequestMapping(value="/updateArrival", method=RequestMethod.POST)
	public String updateArrival(@Valid Arrival arrival,int userId,
			String action, BindingResult result){
		arrival.setUser(usersDao.initRole(userId));
		if(!action.equals("cancel") && !result.hasErrors()){
			arrivalsDao.update(arrival);
		}
		return "redirect:/management/arrivals";
	}
	
	@RequestMapping(value="/deleteArrival",method=RequestMethod.POST)
	public String deleteArrival(int id){
		arrivalsDao.delete(id);
		return "redirect:/management/arrivals";
	}
	
	//end arrivals
}  