package com.models.entityModels;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.dao.DevicesDao;
import com.entities.Device;


@Repository
@Transactional
public class DeviceImpllDao 	extends RootModel
								implements DevicesDao {
	
	@Autowired
	public DeviceImpllDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(Device device) {
		currentSession().save(device);
	}

	@Override
	public void update(Device device) {
		currentSession().update(device);
	}

	@Override
	public void delete(int id) {
		currentSession().delete(this.findById(id));;
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
	public Device initProxy(Device device) {
		Device attchDevice = findById(device.getId());
		Hibernate.initialize(attchDevice.getModel());
		Hibernate.initialize(attchDevice.getScreenSize());
		return attchDevice;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getAllDeviceValues() {
		return currentSession().createCriteria(Device.class).list();
	}

}
