package com.dao;

import java.util.List;

import com.entities.Device_ScreenSize;

public interface Device_ScreenSizeDao {
	public void create(Device_ScreenSize ds);
	public void update(Device_ScreenSize ds);
	public void delete(int id);
	public void delete(Device_ScreenSize ds);
	
	public Device_ScreenSize findById(int id);

	
	public List<Device_ScreenSize> getScreenSizeValues();
}
