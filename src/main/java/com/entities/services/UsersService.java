package com.entities.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.entities.User;
import com.entities.Dao.UserRolesDao;
import com.entities.Dao.UsersDao;

@Service
public class UsersService {
	
	@Autowired 
	UsersDao usersDao;
	@Autowired 
	UserRolesDao rolesDao;
	
	
	public void create(User user){
		String encodedPas = user.getPassword();
		encodedPas = new BCryptPasswordEncoder().encode(encodedPas);
		user.setPassword(encodedPas);
		user.setRole(rolesDao.findById(4));
		usersDao.create(user);
	}
}
