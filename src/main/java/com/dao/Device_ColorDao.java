package com.dao;

import java.util.List;

import com.entities.Device;
import com.entities.Device_Color;


public interface Device_ColorDao {
	public void create(Device_Color dc);
	public void update(Device_Color dc);
	public void delete(int id);
	public void delete(Device_Color dc);	
	
	public Device_Color findById(int id);
	public List<Device> getDevices(Device_Color dc);
	
	public List<Device_Color> getAllColorValues();
}
