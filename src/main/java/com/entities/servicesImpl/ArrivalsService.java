package com.entities.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
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
		Device device = entity.getDevice();
		device.setAmountInStock(device.getAmountInStock() + arrival.getAmount());
		entity.setTime(new DateTime().toDate());
		arrivalsDao.create(entity);
		devicesDao.update(device);
		
	}
	
	public void update(ArrivalDto arrival,String login){
		Arrival entity = arrival.getEntity();
		Arrival oldArrival = arrivalsDao.find(entity.getId());
		Device device = oldArrival.getDevice();
		int updatedAmount = device.getAmountInStock() - oldArrival.getAmount()
				+ entity.getAmount();
		
		if (updatedAmount >= 0) {
			oldArrival.setUser(usersDao.findByLogin(login));
			oldArrival.setAmount(entity.getAmount());
			device.setAmountInStock(updatedAmount);	
			arrivalsDao.update(oldArrival);
			devicesDao.update(device);
		}
	}
	
	public void delete(int id){
		Arrival arrival = arrivalsDao.find(id);
		Device device = arrival.getDevice();
		int updatedAmount = device.getAmountInStock() - arrival.getAmount();
		if (updatedAmount >= 0) {
			device.setAmountInStock(updatedAmount);
			arrivalsDao.delete(id);
			devicesDao.update(device);
		}
	}
		
	
	public ArrivalDto getArrivalToCreate(int deviceId, String login){
		Device device = devicesDao.find(deviceId);
		User user = usersDao.findByLogin(login);
		Arrival arrival = new Arrival();
		arrival.setDevice(device);
		arrival.setUser(user);
		return new ArrivalDto(arrival);
	}
	
	
	public ArrivalDto getArrival(int id){
		return new ArrivalDto(arrivalsDao.find(id));
	}
	
	public List<ArrivalDto> getArrivals(){
		List<ArrivalDto> arrivals = new ArrayList<ArrivalDto>();
		
		for(Arrival item : arrivalsDao.getAll()){
			arrivals.add(new ArrivalDto(item));
		}
		return arrivals;
	}
	
	public FilteredCollection<ArrivalDto> getArrivals(Integer page){
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, getArrivals());
	}
}
