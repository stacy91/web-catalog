package com.entities.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.entities.Device;
import com.entities.Dao.BrandsDao;
import com.entities.Dao.DevicesDao;
import com.helpers.DeviceHelper;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;



@Service
public class DevicesService {
	
	@Autowired
	private DevicesDao devicesDao;
	@Autowired
	private BrandsDao brandsDao;
	@Autowired
	private DeviceHelper deviceHelper;

	private final int PAGE_SIZE = 6;
	
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
	
	public FilteredCollection<Device> getFiltered(List<Device> devices,Integer page){
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, devices);
	}
	
	public List<Device> getDevices(Integer brandId, String search){
		
		int brandIdInt = brandId != null ? brandId : 0;
		List<Device> devices = devicesDao.getAllDeviceValues(brandIdInt,search);
		return devices;
		
	}
	
	public List<Device> getDevices(){
		return devicesDao.getAllDeviceValues();
	}
	
	public Device getDeviceWithBrand(int id) {
		return devicesDao.initBrand(id);
	}
	
	public Device getDeviceWithArrivals(int id) {
		return devicesDao.initArrivals(id);
	}

	public Device getDeviceWithO_S(int id) {
		return devicesDao.initOrders_Sales(id);
	}
	
}
