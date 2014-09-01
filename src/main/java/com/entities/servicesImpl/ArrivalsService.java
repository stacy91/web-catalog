package com.entities.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Arrival;
import com.entities.Device;
import com.entities.User;
import com.entities.Dao.ArrivalsDao;
import com.entities.Dao.DevicesDao;
import com.entities.Dao.UsersDao;
import com.entities.dto.ArrivalDto;
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
	
	public void create(ArrivalDto arrival,String login){
		Arrival entity = arrival.getEntity();
		entity.setUser(usersDao.initRole(usersDao.findByLogin(login)));
		arrivalsDao.create(entity);
	}
	
	public void update(ArrivalDto arrival,String login){
		Arrival entity = arrival.getEntity();
		entity.setUser(usersDao.initRole(usersDao.findByLogin(login)));
		arrivalsDao.update(entity);
	}
	
	public void delete(int id){
		arrivalsDao.delete(id);
	}
	
	public ArrivalDto getArrivalToCreate(int deviceId, String login){
		Device device = devicesDao.initBrand(deviceId);
		User user = usersDao.initRole(usersDao.findByLogin(login));
		Arrival arrival = new Arrival();
		arrival.setDevice(device);
		arrival.setUser(user);
		return new ArrivalDto(arrival);
	}
	
	
	public ArrivalDto getArrival(int id){
		return new ArrivalDto(arrivalsDao.initProxy(id));
	}
	
	public List<ArrivalDto> getArrivals(){
		List<ArrivalDto> arrivals = new ArrayList<ArrivalDto>();
		
		for(Arrival item : arrivalsDao.getAllArrivalValues()){
			arrivals.add(new ArrivalDto(item));
		}
		return arrivals;
	}
	
	public FilteredCollection<ArrivalDto> getArrivals(Integer page){
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, getArrivals());
	}
}
