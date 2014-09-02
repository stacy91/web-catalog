package com.entities.Dao;


import com.entities.UserRole;


public interface UserRolesDao extends RootDao<UserRole>{
	
	public UserRole initUsers(int id);
}
