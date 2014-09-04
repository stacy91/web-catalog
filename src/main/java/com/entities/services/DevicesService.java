package com.entities.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.entities.dto.DeviceDto;

public interface DevicesService extends RootService<DeviceDto>{
	
	public final int PAGE_SIZE = 6;
	
	public DeviceDto create(DeviceDto device,MultipartFile image);
	public DeviceDto update(DeviceDto device,MultipartFile image);
	public List<DeviceDto> getAll(Integer brandId, String search);
}
