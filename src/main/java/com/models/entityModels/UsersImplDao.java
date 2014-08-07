package com.models.entityModels;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UsersDao;
import com.entities.Arrival;
import com.entities.Order_Sale;
import com.entities.User;

@Repository
@Transactional
public class UsersImplDao 	extends RootModel
							implements UsersDao {

	@Autowired
	public UsersImplDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(User user) {
		currentSession().save(user);
	}

	@Override
	public void update(User user) {
		currentSession().update(user);
	}

	@Override
	public void delete(int id) {
		currentSession().delete(this.findById(id));
	}

	@Override
	public void delete(User user) {
		currentSession().delete(user);
	}

	@Override
	public User findById(int id) {
		return (User)currentSession().get(User.class, id);
	}

	public User initRole(User user) {
		return initRole(user.getId());
	}
	
	@Override
	public User initRole(int id) {
		User attchUser = findById(id);
		Hibernate.initialize(attchUser.getRole());
		return attchUser;
	}
	
	@Override
	public User initArrivals(User user) {
		return initArrivals(user.getId());
	}
	
	@Override
	public User initArrivals(int id) {
		User attchUser = findById(id);	
		attchUser.getArrivals().size();
		for(Arrival item : attchUser.getArrivals())
		{
			Hibernate.initialize(item.getDevice());
			Hibernate.initialize(item.getDevice().getBrand());
		}
		
		return attchUser;
	}
	
	@Override
	public User initOrders_Sales(User user) {
		return initOrders_Sales(user.getId());
	}
	
	@Override
	public User initOrders_Sales(int id) {
		User attchUser = findById(id);
		attchUser.getOrders_Sales().size();
		for(Order_Sale item : attchUser.getOrders_Sales())
		{
			Hibernate.initialize(item.getDevice());
			Hibernate.initialize(item.getDevice().getBrand());
		}
		
		return attchUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserValues() {
		return currentSession().createCriteria(User.class).list();
	}

}
