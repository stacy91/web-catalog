package com.models.entityModels;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BrandsDao;
import com.entities.Brand;

@Repository
public class BrandModelDao implements BrandsDao {
	
	private SessionFactory sessionFactory;
	
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired
	public void BrandsModelDao(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void create(Brand brand) {
		currentSession().save(brand);		
	}

	@Override
	public void update(Brand brand, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Brand findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
