package com.controller.management;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.servicesImpl.ArrivalsService;
import com.entities.servicesImpl.BrandsService;
import com.entities.servicesImpl.DevicesService;
import com.entities.servicesImpl.Orders_SalesService;
import com.entities.servicesImpl.UsersService;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

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
	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping(value="")
	public String index(ModelMap model,Principal principal){
		
		List<Order_Sale> orders = o_sService.getOrders(principal.getName());
		List<Order_Sale> available = o_sService.findAvailable(orders);
		List<Order_Sale> sales = o_sService.getSales(principal.getName());
		
		model.addAttribute("orders", o_sService.getFilteredCollection(orders, 1).getItems());
		model.addAttribute("ordersCount", orders.size());
		model.addAttribute("availaCount", available.size());
		model.addAttribute("salesCount", sales.size());
		model.addAttribute("date", new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
		return "management";
	}
	
	//Brands
	
	@RequestMapping(value="/brands")  
    public String showBrands(ModelMap model,Integer page) {  

	FilteredCollection<Brand> fBrands = brandsService.getBrands(page);
    model.addAttribute("brands", fBrands.getItems());
    
    FilteredCollectionGenerator.fillPagination(model,fBrands);
    
    return "adminBrands";
    }  
	
	@RequestMapping(value="/addBrand")  
    public String addBrand(ModelMap model,Integer page) {  
		
    model.addAttribute("brand", new Brand());
    model.addAttribute("page", page);
    
    return "adminBrands/add";
    }  
	@RequestMapping(value="/addBrand", method=RequestMethod.POST)  
    public String addBrand(@RequestParam String action, @Valid Brand brand,BindingResult result,
    		Integer page) {  
		
	String redirect = "redirect:/management/brands"; 	
	if(!action.equals("cancel"))
	{
		if(result.hasErrors())
			return "adminBrands/add";
		brandsService.create(brand);	
	}
	
	if(page != null){
		redirect += "?page=" + page;
	}
	return redirect;		
	} 
	
	@RequestMapping(value="/updateBrand")
	public String updateBrand(int id, ModelMap model,Integer page){
		Brand brandToUpdate = brandsService.findById(id);
		if(brandToUpdate == null)
			return "redirect:/management/brands";
		model.addAttribute("brand", brandToUpdate);
		
		return "adminBrands/update";
	}
	@RequestMapping(value="/updateBrand",method=RequestMethod.POST)
	public String updateBrand(@RequestParam String action,@Valid Brand brand, BindingResult result,
			Integer page){
		
		String redirect = "redirect:/management/brands"; 
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			brandsService.update(brand);	
		}
		
		if(page != null){
			redirect += "?page=" + page;
		}
		
		return redirect;
	}
	
	
	@RequestMapping(value="/deleteBrand", method=RequestMethod.POST)	
	public String deleteBrand(int id,Integer page){
		String redirect = "redirect:/management/brands";
		brandsService.delete(id);
		
		if(page != null){
			redirect += "?page=" + page;
		}
		
		return redirect;
	}
	
	//end Brands
	
	//Devices
	
	@RequestMapping(value="/devices")  
    public String showDevices(ModelMap model,Integer page) {  
	
	FilteredCollection<Device> fDevices = devicesService.getDevices(page,null,null);
	List<Device> devices = fDevices.getItems();
    model.addAttribute("devices", devices);
    model.addAttribute("brands", brandsService.getBrands());
    
    FilteredCollectionGenerator.fillPagination(model,fDevices);
    
    return "adminDevices";
    }   
	
	@RequestMapping(value="/addDevice")
	public String addDevice(ModelMap model,Integer page){
		
		model.addAttribute("device", new Device());
		model.addAttribute("brands", brandsService.getBrands());
		model.addAttribute("page", page);
		
		return "adminDevices/add";
	}
	@RequestMapping(value="/addDevice", method=RequestMethod.POST)
	public String addDevice(@Valid Device device,MultipartFile image,
			String action, BindingResult result,Integer page){
		
		String redirect = "redirect:/management/devices";
		
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			devicesService.create(device, image);
		}

		if(page != null)
			redirect += "?page=" + page;
		
		return redirect;
	}
	
	@RequestMapping(value="/updateDevice")
	public String updateDevice(int id,ModelMap model,Integer page){
		
		Device device = devicesService.getDeviceWithBrand(id);
		model.addAttribute("device", device);
		model.addAttribute("brands", brandsService.getBrands());
		model.addAttribute("page", page);
		
		return "adminDevices/update";
	}
	
	
	@RequestMapping(value="/updateDevice", method=RequestMethod.POST)
	public String updateDevice(@Valid Device device,MultipartFile image,
			String action, BindingResult result,Integer page){
		
		String redirect = "redirect:/management/devices";
		if(!action.equals("cancel"))
		{
			if(result.hasErrors())
				return "adminBrands/add";
			devicesService.update(device, image);
		}
		
		if(page != null){
			redirect += "?page=" + page;
		}
		
		return redirect;
	}
	
	@RequestMapping(value="/deleteDevice",method=RequestMethod.POST)
	public String deleteDevice(int id,Integer page){
		
		String redirect = "redirect:/management/devices";
		devicesService.delete(id);
		
		if(page != null){
			redirect += "?page=" + page;
		}
		return redirect;
	}
	//end devices
	
	
	//Arrivals
	
	@RequestMapping(value="/arrivals")  
    public String showArrivals(ModelMap model,Integer page) {  
		
		FilteredCollection<Arrival> fArrivals = arrivalsService.getArrivals(page);
		model.addAttribute("arrivals", fArrivals.getItems());
		FilteredCollectionGenerator.fillPagination(model, fArrivals);
		return  "adminArrivals";
    }   
	
	@RequestMapping(value="/addArrival", method=RequestMethod.GET)
	public String addArrival(ModelMap model,int deviceId,Principal pricipal,Integer page){
		
		
		model.addAttribute("arrival",arrivalsService.getArrivalToCreate(deviceId,pricipal.getName()));
		model.addAttribute("page", page);
		return "adminArrivals/add";
	}
	@RequestMapping(value="/addArrival", method=RequestMethod.POST)
	public String addArrival(@Valid Arrival arrival,Principal principal,
			String action, BindingResult result,Integer page){
		
		String redirect = "redirect:/management/devices";
		if(!action.equals("cancel") && !result.hasErrors()){
				arrivalsService.create(arrival,principal.getName());
		}
		
		if(page != null){
			redirect += "?page=" + page;
		}
		
		return redirect;
	}
	
	@RequestMapping(value="/updateArrival")
	public String updateArrival(int id,ModelMap model,Integer page){
		
		model.addAttribute("arrival", arrivalsService.getArrival(id));
		model.addAttribute("page", page);
		return "adminArrivals/update";
	}
	@RequestMapping(value="/updateArrival", method=RequestMethod.POST)
	public String updateArrival(@Valid Arrival arrival,Principal principal,
			String action, BindingResult result,Integer page){
		
		String redirect = "redirect:/management/arrivals";
		if(!action.equals("cancel") && !result.hasErrors()){
			arrivalsService.update(arrival,principal.getName());
		}
		
		if(page != null){
			redirect += "?page=" + page;
		}
		
		return redirect;
	}
	
	@RequestMapping(value="/deleteArrival",method=RequestMethod.POST)
	public String deleteArrival(int id,Integer page){
		
		String redirect = "redirect:/management/arrivals";
		arrivalsService.delete(id);
		
		if(page != null){
			redirect += "?page=" + page;
		}
		
		return redirect;
	}
	
	//end arrivals
	
	
	
	//orders_sales
	
	@RequestMapping(value="/allOS")
	public String showOS(ModelMap model,Integer page, String show){
		List<Order_Sale> o_s = null;	
		if(show != null && !show.isEmpty())
		{
			switch (show) {
			case "orders":
				o_s = o_sService.getAllOrders();
				break;
			case "sales":
				o_s = o_sService.getAllSales();
				break;
			
		}}
		else {
			o_s = o_sService.getAllOS();
		}		
		FilteredCollection<Order_Sale> fO_S = o_sService.getFilteredCollection(o_s, page);
		model.addAttribute("o_s", fO_S.getItems());	
		model.addAttribute("show", show);
		
		FilteredCollectionGenerator.fillPagination(model, fO_S);
		
		return "admin/AllOS";
	}
	
	@RequestMapping(value="/deleteOS",method=RequestMethod.POST)
	public String deleteOS(int id,Integer page, String show){
		String redirect = "redirect:/management/allOS";
		o_sService.deleteOS(id);
		if(show != null)
			redirect += "?show=" + show;
		if(page != null)
			redirect += "&page=" + page;
		return redirect;
	}
	
	@RequestMapping(value="/orders")
	public String showOrders(ModelMap model,Principal principal,Integer page,String show){
		
		List<Order_Sale> o_s = o_sService.getOrders(principal.getName());
		if(show != null && show.equals("available")){
			o_s = o_sService.findAvailable(o_s);
		}
		else{
			show = "all";
		}
		FilteredCollection<Order_Sale> fO_S = o_sService.getFilteredCollection(o_s,page);
		model.addAttribute("show", show);
		model.addAttribute("orders", fO_S.getItems());
		
		FilteredCollectionGenerator.fillPagination(model, fO_S);
		
		return "orders";
	}
	
	@RequestMapping(value="/deleteOrder",method=RequestMethod.POST)
	public String deleteOrder(int id){
		
		o_sService.deleteOrder(id);
		return "redirect:/management/orders";
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
		
		FilteredCollectionGenerator.fillPagination(model, fO_S);
		
		return "sales";
	}
	
	//end orders_sales
	
	
	//Users
	
	
	@RequestMapping(value="users")
	public String showUsers(ModelMap model, Integer page){
		
		FilteredCollection<User> fUsers = usersService.getFilteredCollection(page);
		model.addAttribute("users", fUsers.getItems());
	
		FilteredCollectionGenerator.fillPagination(model, fUsers);
		return "admin/users";
	}
	
	@RequestMapping(value="deleteUser",method=RequestMethod.POST)
	public String deleteUser(ModelMap model,Integer page, int id){
		
		String redirect = "redirect:/management/users";
		usersService.delete(id);
		if(page != null)
			redirect += "?page=" + page;
		return redirect;
	}
	
	@RequestMapping(value="changeRole",method=RequestMethod.POST)
	public String changeRole(ModelMap model,Integer page,int id,Principal principal){
		
		String redirect = "redirect:/management/users";
		
		usersService.changeRole(id, principal.getName());
		if(page != null)
			redirect += "?page=" + page;
		return redirect;
	}
	
	@RequestMapping(value="user")
	public String showUser(ModelMap model, Principal principal){
		
		model.addAttribute("user", usersService.findUser(principal.getName()));
		return "user";
	}
	
	@RequestMapping(value="user",method=RequestMethod.POST)
	public String updateUser(ModelMap model, User user,
			String oldPassword,String newPassword,String repeatPassword,String action){
		
		User updatedUser = usersService.update(user,oldPassword, newPassword, repeatPassword);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(updatedUser.getLogin(),
				 updatedUser.getPassword()); 
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/management";
	}
	//end Users
	
	
	
	//statistics
	
	@RequestMapping(value="statistics")
	public String showStatistics(ModelMap model){
		
		
		return "admin/statistics";
	}
	
	//statistics
}  