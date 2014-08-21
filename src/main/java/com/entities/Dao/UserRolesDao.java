package com.entities.Dao;

import java.util.List;

import com.entities.UserRole;


public interface UserRolesDao {
	
	public UserRole findById(int id);
	public UserRole initProxy(UserRole ur);
	public UserRole initProxy(int id);
	
	public List<UserRole> getAllUserRoleValues();
}
