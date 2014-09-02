package com.entities.ImplDao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.Dao.UsersDao;

@Component
@Transactional
public class UsersImplDao 	extends RootDaoImpl<User>
							implements UsersDao {
	
	@SuppressWarnings("unchecked")
	private User confCriteria(int id){
		
		Criteria criteria = currentSession().createCriteria(Order_Sale.class);
		criteria.createAlias("user", "user");
		criteria.createAlias("device", "device");
		criteria.createAlias("user.role", "role");
		criteria.createAlias("device.brand", "brand");		
		criteria.add(Restrictions.eq("user.id", id));
		criteria.addOrder(Order.desc("timeOrdered"));
				
		User user = find(id);
		currentSession().evict(user);
		
		List<Order_Sale> o_s = criteria.list();
		user.setOrders_Sales(o_s);
		
		return user;
	}
	

	@Override
	public User update(User user) {
		User attchUser = find(user.getId());
		attchUser.setLogin(user.getLogin());
		attchUser.setPassword(user.getPassword());
		attchUser.setRole(user.getRole());
		currentSession().update(attchUser);
		return attchUser;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public User findByLogin(String login){
		Criteria criteria = currentSession().createCriteria(User.class);
		List<User> users = criteria.add(Restrictions.eq("login", login)).list();
		return users.get(0);
	}
	
	@Override
	public User initArrivals(int id) {
		User attchUser = find(id);	
		attchUser.getArrivals().size();
		return attchUser;
	}
	
	
	@Override
	public User initOS(int id) {
		
		return confCriteria(id);
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

}
