package com.entities.services;


import java.util.List;

import com.entities.dto.Order_SaleDto;

public interface Orders_SalesService extends RootService<Order_SaleDto>{
	
	public final int PAGE_SIZE = 10;
	
	public Order_SaleDto initOrder_Sale(Integer deviceId,String login,Integer amount);
	public void deleteOS(int id);
	public boolean buy(int id);
	
	public List<Order_SaleDto> getOrders();
	public List<Order_SaleDto> getSales();
	public List<Order_SaleDto> getAll(String show);
	
	public List<Order_SaleDto> getOrders(String login);
	public List<Order_SaleDto> getSales(String login);
	
	List<Order_SaleDto> findAvailable(List<Order_SaleDto> o_s); 
}
