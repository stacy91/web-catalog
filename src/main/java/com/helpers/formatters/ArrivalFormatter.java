package com.helpers.formatters;

import java.util.Locale;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.dao.ArrivalsDao;
import com.dao.BrandsDao;
import com.entities.Arrival;
import com.entities.Brand;


@Component
public class ArrivalFormatter implements Formatter<Arrival> {
	
	@Autowired
	ArrivalsDao arrivalsDao;
	
    @Override
    public String print(Arrival arrival, Locale locale) {
    	int arrivalId = arrival.getId();
        return Integer.toString(arrivalId);
    }

    @Override
    public Arrival parse(String id, Locale locale) throws ParseException {
    	
    	int arrivalId = Integer.parseInt(id);
    	Arrival arrival = arrivalsDao.initProxy(arrivalId);
        return arrival;
    } 


}
