package com.entities.Dao;

import java.util.List;


import com.entities.Device;

public interface DevicesDao {
	
	public void create(Device device);
	public void update(Device newDevice);
	public void delete(int id);
	public void delete(Device device);
	
	public Device findById(int id);

	public Device initBrand(int id);
	public Device initArrivals(int id);
	public Device initOrders_Sales(int id);
	
	public List<Device> getAllDeviceValues();	
}
