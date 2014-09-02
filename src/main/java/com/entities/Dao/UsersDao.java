package com.entities.Dao;

import com.entities.User;

public interface UsersDao extends RootDao<User>{

	public User findByLogin(String login);
	public User initArrivals(int id);
	public User initOS(int id);

}
