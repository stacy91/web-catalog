package com.dao;

import java.util.List;

import com.entities.Device;

public interface DevicesDao {
	
	public void create(Device device);
	public void update(Device device);
	public void delete(int id);
	public void delete(Device device);
	

	public Device findById(int id);
	public Device initProxy(Device device);
	public Device initProxy(int id);
	public List<Device> getAllDeviceValues();	
}
