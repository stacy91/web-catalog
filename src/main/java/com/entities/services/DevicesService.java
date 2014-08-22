package com.entities.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.entities.Device;
import com.entities.Dao.BrandsDao;
import com.entities.Dao.DevicesDao;
import com.helpers.DeviceHelper;

@Service
public class DevicesService {
	
	@Autowired
	DevicesDao devicesDao;
	@Autowired
	BrandsDao brandsDao;
	@Autowired
	private DeviceHelper deviceHelper;
	
	public void create(Device device,MultipartFile image){
		devicesDao.create(device);
		if(!image.isEmpty() && deviceHelper.validateImage(image))
		{
			deviceHelper.saveImage(device.getId(), image);
		}
	}
	
	public void update(Device device, MultipartFile image){
		devicesDao.update(device);
		if(!image.isEmpty() && deviceHelper.validateImage(image))
		{
			deviceHelper.saveImage(device.getId(), image);
		}	
	}
	
	public void delete(int id){
		devicesDao.delete(id);
		deviceHelper.deleteImage(id);
	}
	
	public List<Device> getDevices(){
		return devicesDao.getAllDeviceValues();
	}
	
	public Device getDevicesWithBrand(int id) {
		return devicesDao.initBrand(id);
	}
	
	public Device getDevicesWithArrivals(int id) {
		return devicesDao.initArrivals(id);
	}

	public Device getDevicesWithO_S(int id) {
		return devicesDao.initOrders_Sales(id);
	}
}
