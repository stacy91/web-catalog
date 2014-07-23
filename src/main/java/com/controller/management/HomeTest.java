package com.controller.management;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

import com.entities.Device_Color;

import junit.framework.TestCase;


public class HomeTest extends TestCase{

	private SessionFactory sessionFactory;

	@Override
	protected void setUp() throws Exception {
        sessionFactory = new Configuration()
                .configure() 
                .buildSessionFactory();
	}

	@Override
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}
	
	public void testBasicUsage() {
		Session session = sessionFactory.openSession();
		session.save(new Device_Color("red"));
		session.close();
	}

}
