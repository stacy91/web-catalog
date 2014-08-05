package com.controller.management;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.BrandsDao;
import com.dao.DevicesDao;
import com.dao.UserRolesDao;
import com.dao.UsersDao;


public class RootController {

	protected ApplicationContext ctx;
	protected BrandsDao brandsDao;
	protected DevicesDao devicesDao;
	protected UserRolesDao urDao;
	protected UsersDao uDao;
	
	public RootController() {
		ctx = new ClassPathXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml");
	}
}
