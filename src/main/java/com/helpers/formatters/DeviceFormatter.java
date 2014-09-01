package com.helpers.formatters;

import java.util.Locale;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import com.entities.Dao.DevicesDao;
import com.entities.dto.DeviceDto;


@Component
public class DeviceFormatter implements Formatter<DeviceDto> {
	
	@Autowired
	DevicesDao devicesDao;
	
    @Override
    public String print(DeviceDto device, Locale locale) {
    	int deviceId = device.getId();
        return Integer.toString(deviceId);
    }

    @Override
    public DeviceDto parse(String id, Locale locale) throws ParseException {

    	int deviceId = Integer.parseInt(id);
    	DeviceDto device = new DeviceDto(devicesDao.initBrand(deviceId));
        return device;
    } 


}
