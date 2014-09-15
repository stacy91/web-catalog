package com.entities.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entities.Arrival;
import com.entities.Device;
import com.entities.Dao.ArrivalsDao;
import com.entities.Dao.DevicesDao;
import com.entities.Dao.UsersDao;
import com.entities.dto.ArrivalDto;
import com.entities.services.ArrivalsService;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;


@Service
@Transactional
public class ArrivalsServiceImpl implements ArrivalsService{
	
	@Autowired
	ArrivalsDao arrivalsDao;
	@Autowired
	DevicesDao devicesDao;
	@Autowired
	UsersDao usersDao;
	
	private final int PAGE_SIZE = 10;
	
	@Override
	public ArrivalDto create(ArrivalDto item)
			throws DataIntegrityViolationException {
		Arrival entity = null;
		try{
			
			entity = item.getEntity();
			Device device = entity.getDevice();
			device.setAmountInStock(device.getAmountInStock() + item.getAmount());
			entity.setTime(new DateTime().toDate());
			arrivalsDao.create(entity);
			devicesDao.update(device);
		}
		catch (DataIntegrityViolationException e){
			
		}
		return new ArrivalDto(entity);
	}
	
	@Override
	public ArrivalDto update(ArrivalDto arrival) 
			throws DataIntegrityViolationException{

		Arrival oldArrival = arrivalsDao.initArrival(arrival.getId());
		Device device = oldArrival.getDevice();
		int updatedAmount = device.getAmountInStock() - oldArrival.getAmount()
				+ arrival.getAmount();
		
		if (updatedAmount >= 0) {
			oldArrival.setAmount(arrival.getAmount());
			oldArrival.setPrice(arrival.getPrice());
			oldArrival.setTime(new DateTime().toDate());
			oldArrival.setUser(arrival.getUser().getEntity());
			device.setAmountInStock(updatedAmount);	
			arrivalsDao.update(oldArrival);
			devicesDao.update(device);
			return new ArrivalDto(oldArrival);
		}
		return null;
	}
	
	@Override
	public void delete(int id){
		Arrival arrival = arrivalsDao.initArrival(id);
		Device device = arrival.getDevice();
		int updatedAmount = device.getAmountInStock() - arrival.getAmount();
		if (updatedAmount >= 0) {
			device.setAmountInStock(updatedAmount);
			arrivalsDao.delete(id);
			devicesDao.update(device);
		}
	}
		
	
	@Override
	public ArrivalDto initArrival(int deviceId, String login) {
		Arrival arrival = new Arrival();

		arrival.setDevice(devicesDao.initDevice(deviceId));
		arrival.setUser(usersDao.initUser(login));
		
		return new ArrivalDto(arrival);
	}
	
	
	@Override
	public ArrivalDto find(int id) {
		
		return new ArrivalDto(arrivalsDao.initArrival(id));
	}

	@Override
	public FilteredCollection<ArrivalDto> getFiltered(List<ArrivalDto> items, Integer page) {
		
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, items);
	}

	
	public List<ArrivalDto> getAll() {
		List<ArrivalDto> arrivals = new ArrayList<ArrivalDto>();

		for (Arrival item : arrivalsDao.getAll()) {
			arrivals.add(new ArrivalDto(item));
		}
		return arrivals;
	}

	

}
