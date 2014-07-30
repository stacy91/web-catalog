package com.dao;

import java.util.List;

import com.entities.Arrival;
import com.entities.Order_Sale;
import com.entities.User;
import com.entities.UserRole;

public interface UsersDao {
	public void create(User user);
	public void update(User user);
	public void delete(int id);
	public void delete(User user);
	
	public User findById(int id);
	public UserRole getUserRole(User user);
	public List<Arrival> getArrivals(User user);
	public List<Order_Sale> getOrders_Sales(User user);	
	
	public List<User> getAllUserValues();
}
