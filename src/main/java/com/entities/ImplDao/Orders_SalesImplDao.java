package com.entities.ImplDao;

import java.util.List;

import org.joda.time.DateTime;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entities.Order_Sale;
import com.entities.Dao.Orders_SalesDao;

@Repository
@Transactional
public class Orders_SalesImplDao 	extends RootModel
									implements Orders_SalesDao {
	
	@Autowired
	public Orders_SalesImplDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(Order_Sale o_s) {
		o_s.setTime(new DateTime().toDate());
		currentSession().save(o_s);
	}

	@Override
	public void update(Order_Sale o_s) {
		Order_Sale attchO_S = findById(o_s.getId());
		attchO_S.setIsSold(o_s.getIsSold());
		attchO_S.setTime(new DateTime().toDate());
		currentSession().update(attchO_S);
	}

	@Override
	public void delete(int id) {
		currentSession().delete(findById(id));
	}

	@Override
	public void delete(Order_Sale o_s) {
		currentSession().delete(o_s);
	}

	@Override
	public Order_Sale findById(int id) {
		return (Order_Sale)currentSession().get(Order_Sale.class, id);
	}

	@Override
	public Order_Sale initProxy(Order_Sale o_s) {
		return initProxy(o_s.getId());
	}

	@Override
	public Order_Sale initProxy(int id) {
		Order_Sale attchO_S = findById(id);
		Hibernate.initialize(attchO_S.getDevice());
		Hibernate.initialize(attchO_S.getUser());
		Hibernate.initialize(attchO_S.getDevice().getBrand());
		Hibernate.initialize(attchO_S.getUser().getRole());
		return attchO_S;
	}


	@Override
	public List<Order_Sale> getAllOrders() {
		return confCriteria(false);
	}
	

	@Override
	public List<Order_Sale> getAllSales() {
		return confCriteria(true);
	}
	
	@Override
	public List<Order_Sale> getAll() {
		return confCriteria(null);
	}
	
	@SuppressWarnings("unchecked")
	private List<Order_Sale> confCriteria(Boolean isSold){
		
		Criteria criteria = currentSession().createCriteria(Order_Sale.class);
		criteria.createAlias("user", "user");
		criteria.createAlias("device", "device");
		criteria.createAlias("user.role", "role");
		criteria.createAlias("device.brand", "brand");
		criteria.setFetchMode("user", FetchMode.JOIN);
		criteria.setFetchMode("device", FetchMode.JOIN);
		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.setFetchMode("brand", FetchMode.JOIN);
		
		if(isSold != null){
			criteria.add(Restrictions.eq("isSold", isSold));
		}
		criteria.addOrder(Order.desc("time")).addOrder(Order.asc("role.name")).addOrder(Order.asc("user.login"));
		List<Order_Sale> o_s = criteria.list();
		return o_s;
	}

}
