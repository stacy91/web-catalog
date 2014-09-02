package com.entities.ImplDao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Arrival;
import com.entities.Device;
import com.entities.Dao.ArrivalsDao;

/*я так посмотрел, у меня уже было 
transactional над каждой реализацией 
дао, счас вспомнил что без него
просто не работало еще когда только 
начинал делать эти. Счас проверил, просто в коде метод 
update изменил:
currentSession().save(arrival);
currentSession().update(null) 
и база не обновилась новым поступлением
*/


@Component
@Transactional
public class ArrivalsImplDao 	extends RootDaoImpl<Arrival>
								implements ArrivalsDao {

	@Override
	public Arrival create(Arrival arrival) {
		Device device = arrival.getDevice();
		device.setAmountInStock(device.getAmountInStock() + arrival.getAmount());
		currentSession().save(arrival);
		currentSession().update(device);
		
		return arrival;
	}

	@Override
	public Arrival update(Arrival arrival) {
		Arrival attchArrival = find(arrival.getId());
		Device device = attchArrival.getDevice();
		int updatedAmount = device.getAmountInStock() - attchArrival.getAmount() + arrival.getAmount();
		if(updatedAmount >= 0){
			attchArrival.setTime(new DateTime().toDate());
			attchArrival.setAmount(arrival.getAmount());
			attchArrival.setPrice(arrival.getPrice());
			attchArrival.setUser(arrival.getUser());
			device.setAmountInStock(updatedAmount);
			currentSession().update(attchArrival);
			currentSession().update(device);		
		}
		return attchArrival;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Arrival> getAll() {
		List<Arrival> arrivals = currentSession().
				createCriteria(Arrival.class).addOrder(Order.asc("time")).
				list();

		return arrivals;
	}

}
