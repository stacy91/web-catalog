package com.models.entityModels;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.Device_ScreenSizeDao;
import com.entities.Brand;
import com.entities.Device_ScreenSize;

@Repository
@Transactional
public class Device_ScreenSizeImplDao 	extends RootModel
										implements Device_ScreenSizeDao {
	
	@Autowired
	public Device_ScreenSizeImplDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(Device_ScreenSize dss) {
		currentSession().save(dss);
	}

	@Override
	public void update(Device_ScreenSize dss) {
		currentSession().update(dss);
	}

	@Override
	public void delete(int id) {
		currentSession().delete(this.findById(id));
		
	}

	@Override
	public void delete(Device_ScreenSize dss) {
		currentSession().delete(dss);
	}

	@Override
	public Device_ScreenSize findById(int id) {
		return (Device_ScreenSize)currentSession().get(Device_ScreenSize.class,id);
	}

	@Override
	public Device_ScreenSize initProxy(Device_ScreenSize dss) {
		Device_ScreenSize attchdDss = this.findById(dss.getId());
		Hibernate.initialize(attchdDss.getDevice());
		return attchdDss;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device_ScreenSize> getAllScreenSizeValues() {
		return currentSession().createCriteria(Brand.class).list();
	}

}
