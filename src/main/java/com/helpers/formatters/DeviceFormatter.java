package com.helpers.formatters;

import java.util.Locale;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.entities.Device;
import com.entities.Dao.DevicesDao;


@Component
public class DeviceFormatter implements Formatter<Device> {
	
	@Autowired
	DevicesDao devicesDao;
	
    @Override
    public String print(Device device, Locale locale) {
    	int deviceId = device.getId();
        return Integer.toString(deviceId);
    }

    @Override
    public Device parse(String id, Locale locale) throws ParseException {

        // IMPORTANT: This approach works only if your equals() method doesn't compare fields
        // beyond the ID. If it does, then you'll need those fields set too. Consider simply
        // loading the entity from the database.
    	int deviceId = Integer.parseInt(id);
    	Device device = devicesDao.initBrand(deviceId);
        return device;
    } 


}
