package com.entities.Dao;

import com.entities.Arrival;

public interface ArrivalsDao extends RootDao<Arrival>{
	
	public Arrival initArrival(int id);
}
