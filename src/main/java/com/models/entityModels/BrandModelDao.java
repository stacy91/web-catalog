package com.models.entityModels;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BrandDao;
import com.entities.Brand;

@Repository
@Transactional
@SuppressWarnings("unused")
public class BrandModelDao extends RootModel
							implements BrandDao {

	@Autowired
	public BrandModelDao(SessionFactory sessionFactory) {
		super(sessionFactory);
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
