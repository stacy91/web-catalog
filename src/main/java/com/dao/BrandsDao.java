package com.dao;

import java.util.List;

import com.entities.Brand;
import com.entities.Device;
import com.entities.Device_Model;

public interface BrandsDao {
	
	public void create(Brand brand);
	public void update(Brand brand);
	public void delete(int id);
	public void delete(Brand brand);
	
	public Brand findById(int id);
	
	public List<Device_Model> getDeviceModels(Brand brand);
	public List<Device> getDevices(Device device);
	
	public List<Brand> getAllBrandValues();
	
}
