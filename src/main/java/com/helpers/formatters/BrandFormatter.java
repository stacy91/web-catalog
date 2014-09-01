package com.helpers.formatters;

import java.util.Locale;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.entities.Brand;
import com.entities.Dao.BrandsDao;
import com.entities.dto.BrandDto;


@Component
public class BrandFormatter implements Formatter<BrandDto> {
	
	@Autowired
	BrandsDao brandsDao;
	
    @Override
    public String print(BrandDto brand, Locale locale) {
    	int brandId = brand.getId();
        return Integer.toString(brandId);
    }

    @Override
    public BrandDto parse(String id, Locale locale) throws ParseException {

    	int brandId = Integer.parseInt(id);
    	Brand brand = brandsDao.findById(brandId);
        return new BrandDto(brand);
    } 

}
