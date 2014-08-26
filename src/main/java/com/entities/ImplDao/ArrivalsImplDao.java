package com.entities.ImplDao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entities.Arrival;
import com.entities.Device;
import com.entities.Dao.ArrivalsDao;

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
		arrival.setTime(new DateTime().toDate());
		Device device = getAttchDevice(arrival);
		device.setAmountInStock(device.getAmountInStock() + arrival.getAmount());
		currentSession().update(device);
		currentSession().save(arrival);
	}

	@Override
	public void update(Arrival arrival) {
		Arrival attchArrival = initProxy(arrival.getId());
		Device attchDevice = getAttchDevice(attchArrival);
		int updatedAmount = attchDevice.getAmountInStock() - attchArrival.getAmount() + arrival.getAmount();
		if(updatedAmount >= 0){
			attchArrival.setTime(new DateTime().toDate());
			attchArrival.setAmount(arrival.getAmount());
			attchArrival.setPrice(arrival.getPrice());
			attchArrival.setUser(arrival.getUser());
			attchDevice.setAmountInStock(updatedAmount);
			currentSession().update(attchDevice);
			currentSession().update(attchArrival);
		}		
	}

	@Override
	public void delete(int id) {
		delete(findById(id));
	}

	@Override
	public void delete(Arrival arrival) {
		Device attchDevice = getAttchDevice(arrival);
		int updatedAmount = attchDevice.getAmountInStock() - arrival.getAmount();
		if(updatedAmount >= 0){
			attchDevice.setAmountInStock(updatedAmount);
			currentSession().update(attchDevice);
			currentSession().delete(arrival);
		}	
	}

	@Override
	public Arrival findById(int id) {
		return (Arrival)currentSession().get(Arrival.class, id);
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
	public List<Arrival> getAllArrivalValues() {
		List<Arrival> arrivals = currentSession().createCriteria(Arrival.class).list();
		for(Arrival arrival : arrivals){
			Hibernate.initialize(arrival.getDevice());
			Hibernate.initialize(arrival.getUser());
			Hibernate.initialize(arrival.getDevice().getBrand());
			Hibernate.initialize(arrival.getUser().getRole());
		}
		return arrivals;
	}
	
	private Device getAttchDevice(Arrival arrival){
		return (Device)currentSession().get(Device.class, arrival.getDevice().getId());
	}

}
