package com.entities.Dao;


import com.entities.Brand;


public interface BrandsDao extends RootDao<Brand>{
	
	public Brand initDevices(int id);
	public Brand find(String brandName);
	
}
