package com.entities.ImplDao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.entities.Arrival;
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.Dao.UsersDao;

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
		User attchUser = findById(user.getId());
		attchUser.setLogin(user.getLogin());
		attchUser.setPassword(user.getPassword());
		attchUser.setRole(user.getRole());
		currentSession().update(attchUser);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByLogin(String login){
		Criteria criteria = currentSession().createCriteria(User.class);
		criteria.createAlias("role", "role");

		criteria.setFetchMode("role", FetchMode.JOIN);
		List<User> users = criteria.add(Restrictions.eq("login", login)).list();
		return users.get(0);
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
	public User initOrders(User user) {
		return initOrders(user.getId());
	}
	
	@Override
	public User initSales(User user) {
		return initSales(user.getId());
	}
	
	
	@Override
	public User initOrders(int id) {
		
		return confCriteria(id, false);
	}
	
	@Override
	public User initSales(int id) {
		
		return confCriteria(id, true);
	}
	
	@SuppressWarnings("unchecked")
	private User confCriteria(int id, boolean isSold){
		
		Criteria criteria = currentSession().createCriteria(Order_Sale.class);
		criteria.createAlias("user", "user");
		criteria.createAlias("device", "device");
		criteria.createAlias("user.role", "role");
		criteria.createAlias("device.brand", "brand");
		
		criteria.setFetchMode("user", FetchMode.JOIN);
		criteria.setFetchMode("device", FetchMode.JOIN);
		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.setFetchMode("brand", FetchMode.JOIN);
		
		criteria.add(Restrictions.eq("user.id", id)).add(Restrictions.eq("isSold", isSold));
		criteria.addOrder(Order.desc("time"));
		User user = findById(id);
		currentSession().evict(user);
		
		List<Order_Sale> o_s = criteria.list();
		user.setOrders_Sales(o_s);
		
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserValues() {
		Criteria criteria = currentSession().createCriteria(User.class);
		criteria.createAlias("role", "role");
		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.addOrder(Order.asc("role")).addOrder(Order.asc("login"));
		return criteria.list();
	}

}
