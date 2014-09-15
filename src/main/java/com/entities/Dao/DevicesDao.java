package com.entities.Dao;

import java.util.List;
import com.entities.Device;


public interface DevicesDao extends RootDao<Device> {
	
	public Device initDevice(int id);
	public Device initArrivals(int id);
	public Device initOrders_Sales(int id);
	public Device find(String model);
	public List<Device> getAll(String search);

}
