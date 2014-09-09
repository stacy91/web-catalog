package com.entities.ImplDao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import com.entities.Arrival;
import com.entities.Dao.ArrivalsDao;



@Component
public class ArrivalsImplDao 	extends RootDaoImpl<Arrival>
								implements ArrivalsDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<Arrival> getAll() {
		List<Arrival> arrivals = currentSession().
				createCriteria(Arrival.class).addOrder(Order.desc("time")).
				list();

		return arrivals;
	}

}
