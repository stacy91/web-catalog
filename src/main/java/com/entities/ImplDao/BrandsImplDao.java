package com.entities.ImplDao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import com.entities.Brand;
import com.entities.Dao.BrandsDao;


@Component
public class BrandsImplDao 	extends RootDaoImpl<Brand>
							implements BrandsDao {


	@Override
	public Brand initDevices(int id) {
		Brand br = find(id);
		Hibernate.initialize(br);
		br.getDevices().size();
		return br;
	}
	
	@SuppressWarnings("unchecked")
	@Override 
	public List<Brand> getAll(){	
		return currentSession().createCriteria(Brand.class)
				.addOrder(Order.asc("brandName")).list();
	}
	
}
