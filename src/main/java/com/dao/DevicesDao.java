package com.dao;

import java.util.List;

import com.entities.Device;

public interface DevicesDao {
	
	public void create(Device device);
	public void update(Device device);
	public void delete(int id);
	public void delete(Device device);
	
	/*public Brand getBrand(Device device);
	public Model getDeviceModel(Device device);
	public Device_ScreenSize getScreenSize(Device device);*/
	public Device findById(int id);
	public Device initProxy(Device device);
	
	public List<Device> getAllDeviceValues();
	
	/*public List<Arrival> getArrivals(Device device);
	public List<Order_Sale> getOrdersSales(Device device);*/
	
	
}
