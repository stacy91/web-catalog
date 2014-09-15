package com.entities.dto;

import org.springframework.stereotype.Component;

import com.entities.Brand;
import com.entities.Device;
import com.entities.Order_Sale;

@Component
public class DtoToEntity {

	public Brand convert(BrandDto dto) {

		Brand entity = new Brand(dto.getId(), dto.getBrandName());
		return entity;
	}

	public Device convert(DeviceDto dto) {

		Device entity = new Device(dto.getId(), dto.getBrand() == null ? null
				: convert(dto.getBrand()), dto.getModel(), dto.getPrice(),
				dto.getAmountInStock());
		return entity;
	}

	public Order_Sale convert(Order_SaleDto dto){
		
		Order_Sale entity = new Order_Sale(dto.getId(),
				dto.getDevice() == null ? null : convert(dto.getDevice()),
						dto.getUser() == null ? null : dto.getUser().getEntity(),
								dto.getIsSold(),dto.getAmount(),dto.getTimeOrdered(),dto.getTimeSold());
		
		return entity;
	}
}
