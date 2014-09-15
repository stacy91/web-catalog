package com.entities.ImplDao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
		
		Criteria criteria = currentSession().createCriteria(Arrival.class);
		criteria.addOrder(Order.desc("time"));
		criteria.createAlias("device", "device");
		criteria.createAlias("user", "user");
		criteria.setFetchMode("device", FetchMode.JOIN);
		criteria.setFetchMode("user", FetchMode.JOIN);
		criteria.setFetchMode("device.brand", FetchMode.JOIN);
		criteria.setFetchMode("user.role", FetchMode.JOIN);
		return criteria.list();
	}

	@Override
	public Arrival initArrival(int id) {

		Arrival arrival = find(id);
		arrival.getDevice().getBrand().getBrandName();
		arrival.getUser().getRole().getName();
		return arrival;
	}
	
	
}
