package com.entities.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Brand;
import com.entities.Dao.BrandsDao;
import com.entities.dto.BrandDto;
import com.entities.dto.DtoToEntity;
import com.entities.services.BrandsService;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

@Service
@Transactional
public class BrandsServiceImpl implements BrandsService {

	@Autowired
	private BrandsDao brandsDao;
	@Autowired
	private DtoToEntity toEntity;
	
	private final int PAGE_SIZE = 5;

	@Override
	public BrandDto create(BrandDto item){
		Brand brand = brandsDao.find(item.getBrandName());
		if(brand == null){
			brand = brandsDao.create(toEntity.convert(item));
			return new BrandDto(brand);
		}
		return null;
	}

	@Override
	public BrandDto update(BrandDto item){
		Brand brand = brandsDao.find(item.getBrandName());
		if(brand == null){
		    brand = brandsDao.update(toEntity.convert(item));
			return new BrandDto(brand);
		}
		return null;
	}

	@Override
	public void delete(int id) {

		brandsDao.delete(id);

	}

	@Override
	public BrandDto find(int id) {
		
		Brand brand = brandsDao.find(id);
		if(brand != null)
			return new BrandDto(brand);
		return null;
	}

	@Override
	public FilteredCollection<BrandDto> getFiltered(List<BrandDto> items,
			Integer page) {
		return FilteredCollectionGenerator.getFilteredCollection(page,
				PAGE_SIZE, items);
	}

	@Override
	public List<BrandDto> getAll() {
		List<BrandDto> brands = new ArrayList<BrandDto>();
		for (Brand item : brandsDao.getAll()) {
			brands.add(new BrandDto(item));
		}
		return brands;
	}

}
