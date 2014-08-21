package com.entities.ImplDao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Arrival;
import com.entities.Device;
import com.entities.Order_Sale;
import com.entities.Dao.DevicesDao;


@Repository
@Transactional
public class DeviceImpllDao 	extends RootModel
								implements DevicesDao {
	
	@Autowired
	public DeviceImpllDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(Device device){
		currentSession().save(device);
	}

	@Override
	public void update(Device newDevice) {
		Device deviceToUpdate = findById(newDevice.getId());
		deviceToUpdate.setBrand(newDevice.getBrand());
		deviceToUpdate.setModel(newDevice.getModel());
		deviceToUpdate.setPrice(newDevice.getPrice());
		deviceToUpdate.setHasImage(newDevice.isHasImage());
		currentSession().update(deviceToUpdate);
	}

	@Override
	public void delete(int id) {
		this.delete(this.findById(id));
	}

	@Override
	public void delete(Device device) {
		currentSession().delete(device);
	}

	@Override
	public Device findById(int id) {
		Device device = (Device)currentSession().get(Device.class,id);	
		return device;
	}

	
	@Override
	public Device initBrand(int id) {
		Device attchDevice = findById(id);
		Hibernate.initialize(attchDevice.getBrand());
		return attchDevice;
	}
	
	
	@Override
	public Device initArrivals(int id) {
		Device attchDevice = findById(id);

		attchDevice.getArrivals().size();
		for(Arrival item : attchDevice.getArrivals())
		{
			Hibernate.initialize(item.getUser());
			Hibernate.initialize(item.getUser().getRole());
		}	
		return attchDevice;
	}
	
	
	@Override
	public Device initOrders_Sales(int id) {
		Device attchDevice = findById(id);
		attchDevice.getOrders_Sales().size();
		for(Order_Sale item : attchDevice.getOrders_Sales())
		{
			Hibernate.initialize(item.getUser());
			Hibernate.initialize(item.getUser().getRole());
		}
		return attchDevice;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getAllDeviceValues() {
		List<Device> devices = currentSession().createCriteria(Device.class).list();
		for(Device device : devices)
		{
			Hibernate.initialize(device.getBrand());
		}
		
		return devices;
	}

}
