package com.entities.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.entities.Device;
import com.entities.Dao.BrandsDao;
import com.entities.Dao.DevicesDao;
import com.entities.dto.DeviceDto;
import com.entities.services.DevicesService;
import com.helpers.DeviceHelper;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;



@Service
@Transactional
public class DevicesServiceImpl implements DevicesService{
	
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
	
	@Override
	public DeviceDto create(DeviceDto device,MultipartFile image)
			throws DataIntegrityViolationException{
		Device entity = device.getEntity();
		devicesDao.create(entity);
		
		if(!image.isEmpty() && deviceHelper.validateImage(image))
		{
			deviceHelper.saveImage(device.getId(), image);
		}
		
		return new DeviceDto(entity);
	}
	
	public DeviceDto update(DeviceDto device, MultipartFile image)
			throws DataIntegrityViolationException{
		devicesDao.update(device.getEntity());
		if(!image.isEmpty() && deviceHelper.validateImage(image))
		{
			deviceHelper.saveImage(device.getId(), image);
		}
		return device;
	}
	
	@Override
	public DeviceDto find(int id) {
		
		return new DeviceDto(devicesDao.find(id));
	}
	
	public void delete(int id)
			throws DataIntegrityViolationException{
		devicesDao.delete(id);
		deviceHelper.deleteImage(id);
	}
	
	public FilteredCollection<DeviceDto> getFiltered(List<DeviceDto> devices,Integer page){
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, devices);
	}
	
	public List<DeviceDto> getAll(Integer brandId, String search){
		
		List<Device> devices = null;
		
		if(brandId == null){
			devices = devicesDao.getAll(search);
		}
		else
			devices = brandsDao.initDevices(brandId).getDevices();	
		return convertToDto(devices);
		
	}
	
	@Override
	public List<DeviceDto> getAll() {
		
		return convertToDto(devicesDao.getAll());
	}

	@Override
	public DeviceDto create(DeviceDto item)
			throws DataIntegrityViolationException {
		Device entity = item.getEntity();
		devicesDao.create(entity);
		return new DeviceDto(entity);
	}

	@Override
	public DeviceDto update(DeviceDto item)
			throws DataIntegrityViolationException {
		devicesDao.update(item.getEntity());
		return new DeviceDto(item.getEntity());
	}
	
	

	


	
		
}
