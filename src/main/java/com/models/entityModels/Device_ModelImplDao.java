package com.models.entityModels;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.Device_ModelDao;
import com.entities.Brand;
import com.entities.Device;
import com.entities.Device_Model;

@Repository
@Transactional
public class Device_ModelImplDao extends RootModel
								implements Device_ModelDao {
	
	@Autowired
	public Device_ModelImplDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(Device_Model dm) {
		currentSession().save(dm);
	}

	@Override
	public void update(Device_Model dm) {
		currentSession().update(dm);
	}

	@Override
	public void delete(int id) {
		currentSession().delete(this.findById(id));
	}

	@Override
	public void delete(Device_Model dm) {
		currentSession().delete(dm);
	}

	@Override
	public Device_Model findById(int id) {
		Device_Model dm = (Device_Model)currentSession().get(Device_Model.class,id);
		return dm;			
	}
	
	@Override
	public Device_Model initProxy(Device_Model dm) {
		Device_Model dm1 = findById(dm.getId());
		Hibernate.initialize(dm1.getBrand());
		return dm1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device_Model> getAllModelValues() {
		return currentSession().createCriteria(Device_Model.class).list();
	}


}
