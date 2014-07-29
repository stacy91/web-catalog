package com.models.entityModels;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BrandsDao;
import com.entities.Brand;
import com.entities.Device_Model;

@Repository
@Transactional
@SuppressWarnings("unused")
public class BrandsImplDao extends RootModel
							implements BrandsDao {

	@Autowired
	public BrandsImplDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(Brand brand) {
		currentSession().save(brand);		
	}

	@Override
	public void update(Brand brand) {
		currentSession().update(brand);
	}

	@Override
	public void delete(int id) {
		
		currentSession().delete(this.findById(id));;
	}
	
	@Override
	public void delete(Brand brand) {
		
		currentSession().delete(brand);
	}

	@Override
	public Brand findById(int id) {
		
		return (Brand)currentSession().get(Brand.class,id);
	}
	
	@SuppressWarnings("unchecked")
	@Override 
	public List<Brand> getBrands(){
		
		return currentSession().createCriteria(Brand.class).list();
	}
	
	@Override
	public List<Device_Model> getDeviceModels(Brand brand){
		return brand.getDeviceModels();
	}
}
