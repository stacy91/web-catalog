package com.controller.management;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entities.Arrival;
import com.entities.Brand;
import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.services.ArrivalsService;
import com.entities.services.BrandsService;
import com.entities.services.DevicesService;
import com.entities.services.Orders_SalesService;
import com.helpers.FilteredCollection;

@Controller 
@RequestMapping(value="/management")
public class ManagementController{  
	
	@Autowired
	private DevicesService devicesService;
	@Autowired
	private BrandsService brandsService;
	@Autowired
	private ArrivalsService arrivalsService;
	@Autowired 
	private Orders_SalesService o_sService;
	
	@RequestMapping(value="")
	public String index(ModelMap model,Principal principal){
		
		List<Order_Sale> orders = o_sService.getOrders(principal.getName());
		List<Order_Sale> available = o_sService.findAvailable(orders);
		List<Order_Sale> sales = o_sService.getSales(principal.getName());
		
		model.addAttribute("orders", orders);
		
		model.addAttribute("ordersCount", orders.size());
		model.addAttribute("availaCount", available.size());
		model.addAttribute("salesCount", sales.size());
		
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
	List<Device> devices = devicesService.getDevices(null,null,null).getItems();
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
		Device device = devicesService.getDeviceWithBrand(id);
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
	public String addArrival(@Valid Arrival arrival,int deviceId,Principal principal,
			String action, BindingResult result){

		if(!action.equals("cancel") && !result.hasErrors()){
				arrivalsService.create(arrival,deviceId,principal.getName());
		}
		return "redirect:/management/devices";
	}
	
	@RequestMapping(value="/updateArrival")
	public String updateArrival(int id,ModelMap model){
		
		model.addAttribute("arrival", arrivalsService.getArrival(id));
		return "adminArrivals/update";
	}
	@RequestMapping(value="/updateArrival", method=RequestMethod.POST)
	public String updateArrival(@Valid Arrival arrival,Principal principal,
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
	
	
	
	//orders_sales
	
	
	@RequestMapping(value="/orders")
	public String showOrders(ModelMap model,Principal principal,Integer page){
		
		FilteredCollection<Order_Sale> fO_S = o_sService.getFilteredCollection(
				o_sService.getOrders(principal.getName()), page);
		
		List<Order_Sale> o_s = fO_S.getItems();
		
		model.addAttribute("orders", o_s);
		
		model.addAttribute("beginIndex", fO_S.getBegin());
	    model.addAttribute("endIndex", fO_S.getEnd());
	    model.addAttribute("currentIndex", fO_S.getCurrentPage());
		model.addAttribute("totalPages",fO_S.getTotalPages());
		
		return "orders";
	}
	
	@RequestMapping(value="/deleteOrder")
	public String deleteOrder(int id){
		
		o_sService.deleteOrder(id);
		return "redirect:/management/orders";
	}
	
	@RequestMapping(value="/deleteSale")
	public String deleteSale(int id){
		
		o_sService.deleteSale(id);
		return "redirect:/management/sales";
	}
	
	@RequestMapping(value="/order")
	public String order(ModelMap model, int id,String error){
		

		Order_Sale o_s = o_sService.getOrder(id);
		model.addAttribute("order", o_s);
		
		if(error != null && error.equals("true")){
			
			model.addAttribute("message", "Management.order.buy.errorMsg");
		}
			
		return "order";
	}
	
	@RequestMapping(value="/order",method=RequestMethod.POST)
	public String order(ModelMap model, Order_Sale o_s, String action){

		if(!action.equals("cancel")){
			if(!o_sService.buy(o_s.getId()))
				return "redirect:/management/order?id=" + o_s.getId() + "&error=true";
		}
		return "redirect:/management/orders";
	}
	
	@RequestMapping(value="/sales")
	public String showSales(ModelMap model, Principal principal, Integer page){
		
		FilteredCollection<Order_Sale> fO_S = o_sService.getFilteredCollection(
				o_sService.getSales(principal.getName()), page);
				
		model.addAttribute("sales", fO_S.getItems());
		
		model.addAttribute("beginIndex", fO_S.getBegin());
	    model.addAttribute("endIndex", fO_S.getEnd());
	    model.addAttribute("currentIndex", fO_S.getCurrentPage());
		model.addAttribute("totalPages",fO_S.getTotalPages());
		
		return "sales";
	}
	
	//end orders_sales
	
}  