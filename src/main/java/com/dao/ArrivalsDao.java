package com.dao;

import java.util.List;

import com.entities.Arrival;

public interface ArrivalsDao {
	
	public void create(Arrival arrival);
	public void update(Arrival arrival);
	public void delete(int id);
	public void delete(Arrival arrival);
	
	public Arrival findById(int id);
	public Arrival initProxy(Arrival arrival);
	public Arrival initProxy(int id);
	public List<Arrival> getAllDeviceValues();	
}
