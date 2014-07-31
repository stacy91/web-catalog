package com.dao;

import java.util.List;

import com.entities.Device;
import com.entities.Device_ScreenSize;

public interface Device_ScreenSizeDao {
	public void create(Device_ScreenSize dss);
	public void update(Device_ScreenSize dss);
	public void delete(int id);
	public void delete(Device_ScreenSize dss);
	
	public Device_ScreenSize findById(int id);
	public Device_ScreenSize initProxy(Device_ScreenSize dss);
	
	public List<Device_ScreenSize> getAllScreenSizeValues();
}
