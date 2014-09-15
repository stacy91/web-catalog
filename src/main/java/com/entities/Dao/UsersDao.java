package com.entities.Dao;

import com.entities.User;

public interface UsersDao extends RootDao<User>{

	public User find(String login);
	public User initArrivals(int id);
	public User initOS(int id);
	public User initUser(int id);
	public User initUser(String login);

}
