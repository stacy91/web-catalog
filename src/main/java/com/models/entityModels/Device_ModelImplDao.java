package com.models.entityModels;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.Device_ModelDao;
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
		return (Device_Model)currentSession().get(Device_Model.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device_Model> getModels() {
		return currentSession().createCriteria(Device_Model.class).list();
	}

}
