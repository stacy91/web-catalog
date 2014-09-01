package com.entities.servicesImpl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javassist.expr.NewArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.joda.time.DateTime;

import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.Dao.DevicesDao;
import com.entities.Dao.Orders_SalesDao;
import com.entities.Dao.UsersDao;
import com.entities.dto.Order_SaleDto;
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
		o_s.setTimeOrdered((new DateTime().toDate()));
		order_salesDao.create(o_s);
	}
	
	public void update(Order_SaleDto o_s){
		order_salesDao.update(o_s.getEntity());
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
	
	public Order_SaleDto getOrder(int id){
		return new Order_SaleDto(order_salesDao.initProxy(id));
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
	
	public List<Order_SaleDto> getOrders(String login){
		
		User user = usersDao.initOrders(usersDao.findByLogin(login));
		return convertToDto(user.getOrders_Sales());
	}
	
	public List<Order_SaleDto> getSales(String login){
		
		User user = usersDao.initSales(usersDao.findByLogin(login));
		return convertToDto(user.getOrders_Sales());
	}
	
	public List<Order_SaleDto> getAllOS(){
		return convertToDto(order_salesDao.getAll());
	}
	
	public List<Order_SaleDto> getAllOrders(){
		return convertToDto(order_salesDao.getAllOrders());
	}
	
	public List<Order_SaleDto> getAllSales(){
		return convertToDto(order_salesDao.getAllSales());
	}
	
	public FilteredCollection<Order_SaleDto> getFilteredCollection(List<Order_SaleDto> o_s, Integer page){

		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, o_s);
	}
	
	public List<Order_SaleDto> findAvailable(List<Order_SaleDto> o_s){
		
		List<Order_SaleDto> list = new ArrayList<Order_SaleDto>();
		
		for(Order_SaleDto item : o_s){
			int amount = item.getDevice().getAmountInStock() - item.getAmount();
			if(amount >= 0){
				list.add(item);
			}
		}
		return list;
	}
	
	public List<Order_SaleDto> convertToDto(List<Order_Sale> o_s){
		
		List<Order_SaleDto> dto = new ArrayList<Order_SaleDto>();
		for(Order_Sale item : o_s){
			dto.add(new Order_SaleDto(item));
		}
		return dto;
	}
	
}
