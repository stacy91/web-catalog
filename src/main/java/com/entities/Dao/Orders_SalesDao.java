package com.entities.Dao;

import java.util.List;

import com.entities.Order_Sale;

public interface Orders_SalesDao {
	
	public void create(Order_Sale o_s);
	public void update(Order_Sale o_s);
	public void delete(int id);
	public void delete(Order_Sale o_s);
	
	public Order_Sale findById(int id);
	public Order_Sale initProxy(Order_Sale o_s);
	public Order_Sale initProxy(int id);
	public List<Order_Sale> getAllDeviceValues();
}
