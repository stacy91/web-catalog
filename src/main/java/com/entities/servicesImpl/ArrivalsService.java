package com.entities.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Arrival;
import com.entities.Device;
import com.entities.User;
import com.entities.Dao.ArrivalsDao;
import com.entities.Dao.DevicesDao;
import com.entities.Dao.UsersDao;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;


@Service
public class ArrivalsService {
	
	@Autowired
	ArrivalsDao arrivalsDao;
	@Autowired
	DevicesDao devicesDao;
	@Autowired
	UsersDao usersDao;
	
	private final int PAGE_SIZE = 10;
	
	public void create(Arrival arrival,String login){
		arrival.setUser(usersDao.initRole(usersDao.findByLogin(login)));
		arrivalsDao.create(arrival);
	}
	
	public void update(Arrival arrival,String login){
		arrival.setUser(usersDao.initRole(usersDao.findByLogin(login)));
		arrivalsDao.update(arrival);
	}
	
	public void delete(int id){
		arrivalsDao.delete(id);
	}
	
	public Arrival getArrivalToCreate(int deviceId, String login){
		Device device = devicesDao.initBrand(deviceId);
		User user = usersDao.initRole(usersDao.findByLogin(login));
		Arrival arrival = new Arrival();
		arrival.setDevice(device);
		arrival.setUser(user);
		return arrival;
	}
	
	
	public Arrival getArrival(int id){
		return arrivalsDao.initProxy(id);
	}
	
	public List<Arrival> getArrivals(){
		return arrivalsDao.getAllArrivalValues();
	}
	
	public FilteredCollection<Arrival> getArrivals(Integer page){
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, getArrivals());
	}
}
