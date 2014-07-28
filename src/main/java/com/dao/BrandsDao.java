package com.dao;

import com.entities.Brand;

public interface BrandsDao {
	
	public void create(Brand brand);
	public void update(Brand brand, int id);
	public void delete(int id);
	
	public Brand findById(int id);
}
