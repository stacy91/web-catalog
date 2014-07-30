package com.dao;

import java.util.List;

import com.entities.Brand;
import com.entities.Device;
import com.entities.Device_Model;

public interface Device_ModelDao {
	
	public void create(Device_Model dm);
	public void update(Device_Model dm);
	public void delete(int id);
	public void delete(Device_Model dm);
	
	public Device_Model findById(int id);
	public Brand getBrand(Device_Model dm);
	public List<Device> getDevices(Device_Model dm); 
	
	public List<Device_Model> getAllModelValues();
}
