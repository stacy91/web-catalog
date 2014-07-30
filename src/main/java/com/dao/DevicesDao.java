package com.dao;

import com.entities.Device;

public interface DevicesDao {
	
	public void create(Device device);
	public void update(Device device);
	public void delete(int id);
	public void delete(Device device);
	
	public Device findById(int id);
}
