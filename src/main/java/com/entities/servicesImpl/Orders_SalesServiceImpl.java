package com.entities.servicesImpl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.joda.time.DateTime;

import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.Dao.DevicesDao;
import com.entities.Dao.Orders_SalesDao;
import com.entities.Dao.UsersDao;
import com.entities.dto.Order_SaleDto;
import com.entities.services.Orders_SalesService;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;
import com.helpers.Order_SalesHelper;

@Service
@Transactional
public class Orders_SalesServiceImpl implements Orders_SalesService{
	
	@Autowired
	private Orders_SalesDao order_salesDao;
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private DevicesDao devicesDao;
	
	private List<Order_SaleDto> convertToDto(List<Order_Sale> o_s) {

		List<Order_SaleDto> dto = new ArrayList<Order_SaleDto>();
		for (Order_Sale item : o_s) {
			dto.add(new Order_SaleDto(item));
		}
		return dto;
	}
	
	@Override
	public Order_SaleDto create(Order_SaleDto item)
			throws DataIntegrityViolationException {
		
		return new Order_SaleDto(order_salesDao.create(item.getEntity()));
	}

	@Override
	public Order_SaleDto update(Order_SaleDto item)
			throws DataIntegrityViolationException {
		if(item.getIsSold())
			item.setTimeSold(new DateTime().toDate());
		
		return new Order_SaleDto(order_salesDao.update(item.getEntity()));
	}
	
	@Override
	public Order_SaleDto initOrder_Sale(Integer deviceId,String login,Integer amount){
		
		Order_Sale o_s = new Order_Sale();
		o_s.setDevice(devicesDao.find(deviceId));
		o_s.setUser(usersDao.findByLogin(login));
		o_s.setAmount(amount);
		o_s.setTimeOrdered((new DateTime().toDate()));
		return new Order_SaleDto(o_s);
	}

	
	@Override
	public void delete(int id) throws DataIntegrityViolationException {
		Order_Sale o_s = order_salesDao.find(id);
		if(!o_s.getIsSold()){
			order_salesDao.delete(id);
		}	
	}
	
	@Override
	public void deleteOS(int id){
		
		Order_Sale o_s = order_salesDao.find(id);
		if(o_s.getIsSold()){
			Device device = o_s.getDevice();
			device.setAmountInStock(device.getAmountInStock() + o_s.getAmount());
			devicesDao.update(device);		
		}	
		
		order_salesDao.delete(id);	
	}	
	
	
	@Override
	public Order_SaleDto find(int id) {
		return new Order_SaleDto(order_salesDao.find(id));
	}
	
	@Override
	public boolean buy(int id){
		
		boolean result = false;
		Order_Sale o_s = order_salesDao.find(id);
		Device device = o_s.getDevice();
		int totalAmount = device.getAmountInStock() - o_s.getAmount();
		if(totalAmount >= 0){
			o_s.setTimeSold(new DateTime().toDate());
			device.setAmountInStock(totalAmount);
			o_s.setIsSold(true);
			devicesDao.update(device);
			order_salesDao.update(o_s);
			result = true;
		}
		
		return result;
	}
	
	@Override
	public List<Order_SaleDto> getAll() {
		return convertToDto(order_salesDao.getAll());
	}
	
	@Override
	public List<Order_SaleDto> getOrders(String login){
		
		User user = usersDao.initOS(usersDao.findByLogin(login).getId());
		return convertToDto(Order_SalesHelper.getOrders(user.getOrders_Sales()));
	}
	
	@Override
	public List<Order_SaleDto> getSales(String login){
		
		User user = usersDao.initOS(usersDao.findByLogin(login).getId());
		return convertToDto(Order_SalesHelper.getSales(user.getOrders_Sales()));
	}
	
	
	@Override
	public List<Order_SaleDto> getOrders(){
		return convertToDto(Order_SalesHelper.getOrders(order_salesDao.getAll()));
	}
	
	public List<Order_SaleDto> getSales(){
		return convertToDto(Order_SalesHelper.getSales(order_salesDao.getAll()));
	}
	
	@Override
	public FilteredCollection<Order_SaleDto> getFiltered(
			List<Order_SaleDto> items, Integer page) {
		
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, items);
	}
	
	@Override
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
		
}
