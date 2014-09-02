package com.entities.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Brand;
import com.entities.Dao.BrandsDao;
import com.entities.dto.BrandDto;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;


@Service
public class BrandsService {
	
	private final int PAGE_SIZE=5;
	
	@Autowired
	private BrandsDao brandsDao;
	
	public void create(BrandDto brand){
		brandsDao.create(brand.getEntity());
	}
	
	public void update(BrandDto brand){
		brandsDao.update(brand.getEntity());
	}
	
	public void delete(int id){
		brandsDao.delete(id);
	}
	
	public Brand findById(int id){
		return brandsDao.find(id);
	}
	
	public FilteredCollection<BrandDto> getFiltered(Integer page){
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, getBrands());
	}
	
	public List<BrandDto> getBrands() {
		List<BrandDto> brands = new ArrayList<BrandDto>();
		for(Brand item : brandsDao.getAll()){
			brands.add(new BrandDto(item));
		}
		return brands;
	}

}
