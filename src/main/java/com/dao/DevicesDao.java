package com.dao;

import java.util.List;
import org.springframework.ui.Model;
import com.entities.Brand;
import com.entities.Device;
import com.entities.Device_Color;
import com.entities.Device_Memory;
import com.entities.Device_ScreenSize;
import com.entities.Arrival;
import com.entities.Order_Sale;

public interface DevicesDao {
	
	public void create(Device device);
	public void update(Device device);
	public void delete(int id);
	public void delete(Device device);
	
	public Brand getBrand(Device device);
	public Model getDeviceModel(Device device);
	
	public Device_Color getDeviceColor(Device device);
	public Device_Memory getDeviceMemory(Device device);
	public Device_ScreenSize getScreenSize(Device device);
	
	public List<Arrival> getArrivals(Device device);
	public List<Order_Sale> getOrdersSales(Device device);
	
	public Device findById(int id);
}
