package com.models.entityModels;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class RootModel {
	
	protected SessionFactory sessionFactory;
	
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public RootModel(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public RootModel(){
		
	}
}
