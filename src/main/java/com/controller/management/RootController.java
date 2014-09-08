package com.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.entities.services.ArrivalsService;
import com.entities.services.BrandsService;
import com.entities.services.DevicesService;
import com.entities.services.Orders_SalesService;
import com.entities.services.UsersService;

@Controller 
@RequestMapping(value="/management")
public abstract class RootController {
	
	@Autowired
	DevicesService devicesService;
	@Autowired
	BrandsService brandsService;
	@Autowired
	ArrivalsService arrivalsService;
	@Autowired 
	Orders_SalesService o_sService;
	@Autowired
	UsersService usersService;
}
