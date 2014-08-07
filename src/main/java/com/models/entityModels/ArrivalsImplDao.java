package com.models.entityModels;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ArrivalsDao;
import com.entities.Arrival;

@Repository
@Transactional
public class ArrivalsImplDao 	extends RootModel
								implements ArrivalsDao {

	@Autowired
	public ArrivalsImplDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(Arrival arrival) {
		currentSession().save(arrival);
	}

	@Override
	public void update(Arrival arrival) {
		currentSession().update(arrival);
	}

	@Override
	public void delete(int id) {
		currentSession().delete(findById(id));
	}

	@Override
	public void delete(Arrival arrival) {
		currentSession().delete(arrival);
	}

	@Override
	public Arrival findById(int id) {
		return (Arrival)currentSession().get(Arrival.class, id);
	}

	@Override
	public Arrival initProxy(Arrival arrival) {
		return initProxy(arrival.getId());
	}

	@Override
	public Arrival initProxy(int id) {
		Arrival attchArrival = findById(id);
		Hibernate.initialize(attchArrival.getDevice());
		Hibernate.initialize(attchArrival.getUser());
		Hibernate.initialize(attchArrival.getDevice().getBrand());
		Hibernate.initialize(attchArrival.getUser().getRole());
		return attchArrival;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Arrival> getAllDeviceValues() {
		return currentSession().createCriteria(Arrival.class).list();
	}

}
