package com.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.entities.Device;

public interface DevicesDao {
	
	public void create(Device device,MultipartFile image);
	public void update(Device newDevice);
	public void delete(int id);
	public void delete(Device device);
	

	public Device findById(int id);
	
	public Device initBrand(Device device);
	public Device initBrand(int id);
	
	public Device initArrivals(Device device);
	public Device initArrivals(int id);
	
	public Device initOrders_Sales(Device device);
	public Device initOrders_Sales(int id);
	
	public List<Device> getAllDeviceValues();	
}
