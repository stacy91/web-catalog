package com.dao;

import java.util.List;

import com.entities.Device;
import com.entities.Device_Memory;

public interface Device_MemoryDao {
	public void create(Device_Memory dm);
	public void update(Device_Memory dm);
	public void delete(int id);
	public void delete(Device_Memory dm);
	
	public Device_Memory findById(int id);
	public List<Device> getDevices(Device_Memory dm);
	
	public List<Device_Memory> getAllMemoryValues();
}