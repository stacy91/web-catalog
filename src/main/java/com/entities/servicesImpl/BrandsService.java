package com.entities.servicesImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entities.Brand;
import com.entities.Dao.BrandsDao;


@Service
public class BrandsService {
	
	@Autowired
	private BrandsDao brandsDao;
	
	public void create(Brand brand){
		brandsDao.create(brand);
	}
	
	public void update(Brand brand){
		brandsDao.update(brand);
	}
	
	public void delete(int id){
		brandsDao.delete(id);
	}
	
	public Brand findById(int id){
		return brandsDao.findById(id);
	}
	
	public List<Brand> getBrands(){
		return brandsDao.getAllBrandValues();
	}
	
}
