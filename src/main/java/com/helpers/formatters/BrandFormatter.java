package com.helpers.formatters;

import java.util.Locale;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.entities.Brand;
import com.entities.Dao.BrandsDao;


@Component
public class BrandFormatter implements Formatter<Brand> {
	
	@Autowired
	BrandsDao brandsDao;
	
    @Override
    public String print(Brand brand, Locale locale) {
    	int brandId = brand.getId();
        return Integer.toString(brandId);
    }

    @Override
    public Brand parse(String id, Locale locale) throws ParseException {

        // IMPORTANT: This approach works only if your equals() method doesn't compare fields
        // beyond the ID. If it does, then you'll need those fields set too. Consider simply
        // loading the entity from the database.
    	int brandId = Integer.parseInt(id);
    	Brand brand = brandsDao.findById(brandId);
        return brand;
    } 

}
