package com.entities.Dao;

import java.util.List;
import com.entities.Brand;


public interface BrandsDao {
	
	public void create(Brand brand);
	public void update(Brand newBrand);
	public void delete(int id);
	public void delete(Brand brand);
	
	public Brand findById(int id);
	public Brand initProxy(int id);
	public List<Brand> getAllBrandValues();
	
}
