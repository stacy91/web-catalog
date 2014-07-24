package com.controller.management;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  


import com.entities.Device_Color;

@Controller  
public class Home {  
    String message = "Welcome from Home controller !";  
  
    @SuppressWarnings("deprecation")
	@RequestMapping("/hello")  
    public ModelAndView showMessage() {  
    	
    	SessionFactory sessionFactory = new Configuration()
        .configure() 
        .buildSessionFactory();
    	
    	Session session = sessionFactory.openSession();
		session.save(new Device_Color("red"));
		session.close();
    	
        System.out.println("from controller");  
        return new ModelAndView("hello", "message", message);  
    }  
}  