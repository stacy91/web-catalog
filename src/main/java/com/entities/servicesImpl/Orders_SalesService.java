package com.entities.servicesImpl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.Dao.DevicesDao;
import com.entities.Dao.Orders_SalesDao;
import com.entities.Dao.UsersDao;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

@Service
public class Orders_SalesService {
	
	@Autowired
	private Orders_SalesDao order_salesDao;
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private DevicesDao devicesDao;
	
	private final int PAGE_SIZE = 10;

	
	public void create(Integer deviceId,String login,Integer amount){
		
		Order_Sale o_s = new Order_Sale();
		o_s.setDevice(devicesDao.findById(deviceId));
		o_s.setUser(usersDao.findByLogin(login));
		o_s.setAmount(amount);
		order_salesDao.create(o_s);
	}
	
	public void update(Order_Sale o_s){
		order_salesDao.update(o_s);
	}
	
	public void deleteOrder(int id){
		Order_Sale o_s = order_salesDao.initProxy(id);
		if(!o_s.getIsSold()){
			order_salesDao.delete(o_s);
		}
	}
	
	public void deleteOS(int id){
		
		Order_Sale o_s = order_salesDao.initProxy(id);
		if(o_s.getIsSold()){
			Device device = o_s.getDevice();
			device.setAmountInStock(device.getAmountInStock() + o_s.getAmount());
			devicesDao.update(device);		
		}	
		
		order_salesDao.delete(o_s);	
	}
	
	public Order_Sale getOrder(int id){
		return order_salesDao.initProxy(id);
	}
	
	public boolean buy(int id){
		
		boolean result = false;
		Order_Sale o_s = order_salesDao.initProxy(id);
		Device device = o_s.getDevice();
		int totalAmount = device.getAmountInStock() - o_s.getAmount();
		if(totalAmount >= 0){
			device.setAmountInStock(totalAmount);
			o_s.setIsSold(true);
			devicesDao.update(device);
			order_salesDao.update(o_s);
			result = true;
		}
		
		return result;
	}
	
	public List<Order_Sale> getOrders(String login){
		
		User user = usersDao.initOrders(usersDao.findByLogin(login));
		return user.getOrders_Sales();
	}
	
	public List<Order_Sale> getSales(String login){
		
		User user = usersDao.initSales(usersDao.findByLogin(login));
		return user.getOrders_Sales();
	}
	
	public List<Order_Sale> getAllOS(){
		return order_salesDao.getAll();
	}
	
	public List<Order_Sale> getAllOrders(){
		return order_salesDao.getAllOrders();
	}
	
	public List<Order_Sale> getAllSales(){
		return order_salesDao.getAllSales();
	}
	
	public FilteredCollection<Order_Sale> getFilteredCollection(List<Order_Sale> o_s, Integer page){
		int pageInt = page != null ? page - 1 : 0;
		return FilteredCollectionGenerator.getFilteredCollection(pageInt, PAGE_SIZE, o_s);
	}
	
	public List<Order_Sale> findAvailable(List<Order_Sale> o_s){
		
		List<Order_Sale> list = new ArrayList<Order_Sale>();
		
		for(Order_Sale item : o_s){
			int amount = item.getDevice().getAmountInStock() - item.getAmount();
			if(amount >= 0){
				list.add(item);
			}
		}
		return list;
	}
	
}
