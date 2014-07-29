package com.dao;

import java.util.List;
import com.entities.Device_Model;

public interface Device_ModelDao {
	
	public void create(Device_Model dm);
	public void update(Device_Model dm);
	public void delete(int id);
	public void delete(Device_Model dm);
	
	public Device_Model findById(int id);
	
	
	public List<Device_Model> getModels();
}
