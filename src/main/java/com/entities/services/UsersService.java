package com.entities.services;


import com.entities.dto.UserDto;

public interface UsersService extends RootService<UserDto>{
	
	public final int PAGE_SIZE = 10;
	
	public void changeRole(int id, String login);
	public UserDto find(String login);
}
