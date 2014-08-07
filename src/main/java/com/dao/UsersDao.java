package com.dao;

import java.util.List;
import com.entities.User;

public interface UsersDao {
	public void create(User user);
	public void update(User user);
	public void delete(int id);
	public void delete(User user);
	
	public User findById(int id);
	
	public User initRole(User user);
	public User initRole(int id);
	
	public User initArrivals(User user);
	public User initArrivals(int id);
	
	public User initOrders_Sales(User user);
	public User initOrders_Sales(int id);
	
	public List<User> getAllUserValues();
}
