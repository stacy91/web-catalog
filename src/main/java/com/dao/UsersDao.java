package com.dao;

import com.entities.User;

public interface UsersDao {
	public void create(User user);
	public void update(User user);
	public void delete(int id);
	public void delete(User user);
	
	public User findById(int id);
	
	
}
