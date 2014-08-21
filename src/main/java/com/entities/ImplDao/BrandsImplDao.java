package com.entities.ImplDao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.Brand;
import com.entities.Device;
import com.entities.Dao.BrandsDao;


@Repository
@Transactional
@SuppressWarnings("unused")
public class BrandsImplDao 	extends RootModel
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
	public void update(Brand newBrand) 
	{
		Brand brandToUpdate = findById(newBrand.getId());
		brandToUpdate.setBrandName(newBrand.getBrandName());
		currentSession().update(brandToUpdate);
	}

	@Override
	public void delete(int id) {		
		this.delete(this.findById(id));;
	}
	
	@Override
	public void delete(Brand brand) {
		currentSession().delete(brand);
	}

	@Override
	public Brand findById(int id) {
		Brand br = (Brand)currentSession().get(Brand.class,id);
		return br;
	}
	
	
	@Override
	public Brand initProxy(int id) {
		Brand br = findById(id);
		Hibernate.initialize(br);
		br.getDevices().size();
		return br;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override 
	public List<Brand> getAllBrandValues(){
		
		return currentSession().createCriteria(Brand.class).list();
	}
	
	
}
