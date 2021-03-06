package com.entities.ImplDao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.entities.User;
import com.entities.Dao.UsersDao;

@Component
public class UsersImplDao 	extends RootDaoImpl<User>
							implements UsersDao {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public User find(String login){
		Criteria criteria = currentSession().createCriteria(User.class);
		List<User> users = criteria.add(Restrictions.eq("login", login)).list();
		if(users.size() > 0)
			return users.get(0);
		else 
			return null;
	}
	
	@Override
	public User initArrivals(int id) {
		User attchUser = find(id);	
		attchUser.getArrivals().size();
		return attchUser;
	}
	
	
	@Override
	public User initOS(int id) {
		User user = find(id);
		user.getOrders_Sales().size();
		return user;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		Criteria criteria = currentSession().createCriteria(User.class);
		criteria.createAlias("role", "role");
		criteria.setFetchMode("role", FetchMode.JOIN);
		criteria.addOrder(Order.asc("role")).addOrder(Order.asc("login"));
		return criteria.list();
	}

	@Override
	public User initUser(int id) {
		User user = find(id);
		user.getRole().getName();
		return user;
	}

	@Override
	public User initUser(String login) {
		User user = find(login);
		user.getRole().getName();
		return user;
	}

}
