package com.entities.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.entities.Device;
import com.entities.Dao.BrandsDao;
import com.entities.Dao.DevicesDao;
import com.entities.dto.DeviceDto;
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
	
	private List<DeviceDto> convertToDto(List<Device> items){
		
		List<DeviceDto> devices = new ArrayList<DeviceDto>();
		for(Device item : items){
			devices.add(new DeviceDto(item));
		}
		
		return devices;
	}
	
	public void create(DeviceDto device,MultipartFile image){
		Device device2 = device.getEntity();
		devicesDao.create(device2);
		
		if(!image.isEmpty() && deviceHelper.validateImage(image))
		{
			deviceHelper.saveImage(device.getId(), image);
		}
	}
	
	public void update(DeviceDto device, MultipartFile image){
		devicesDao.update(device.getEntity());
		if(!image.isEmpty() && deviceHelper.validateImage(image))
		{
			deviceHelper.saveImage(device.getId(), image);
		}	
	}
	
	public void delete(int id){
		devicesDao.delete(id);
		deviceHelper.deleteImage(id);
	}
	
	public FilteredCollection<DeviceDto> getFiltered(List<DeviceDto> devices,Integer page){
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, devices);
	}
	
	public List<DeviceDto> getDevices(Integer brandId, String search){
		
		int brandIdInt = brandId != null ? brandId : 0;
		return convertToDto(devicesDao.getAll(brandIdInt,search));
		
	}
	
	public List<DeviceDto> getDevices(){
		return convertToDto(devicesDao.getAll());
	}
	
	public DeviceDto getDevice(int id) {
		return new DeviceDto(devicesDao.find(id));
	}
		
}
