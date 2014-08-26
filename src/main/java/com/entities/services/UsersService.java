package com.entities.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.entities.User;
import com.entities.UserRole;
import com.entities.Dao.UserRolesDao;
import com.entities.Dao.UsersDao;
import com.helpers.FilteredCollection;

@Service
public class UsersService {
	
	@Autowired 
	private UsersDao usersDao;
	@Autowired 
	private UserRolesDao rolesDao;
	private final int PAGE_SIZE = 10;
	
	public void create(User user){
		String encodedPas = user.getPassword();
		encodedPas = new BCryptPasswordEncoder().encode(encodedPas);
		user.setPassword(encodedPas);
		user.setRole(rolesDao.findById(4));
		usersDao.create(user);
	}
	
	public void changeRole(int id, String login){
		
		User user = usersDao.initRole(id);
		if(!user.getLogin().equals(login)){
			if(user.getRole().getId() == 3)
				user.setRole(rolesDao.findById(4));
			else
				user.setRole(rolesDao.findById(3));
			usersDao.update(user);
		}
	}
	
	public void delete(int id){
		usersDao.delete(id);
	}
	
	public User update(User user,String oldPassword,String newPassword,String repeatPassword){
		

		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String encodedOldPas = bc.encode(oldPassword);
		
		if(oldPassword != null && newPassword != null & repeatPassword != null){
			if(encodedOldPas.equals(oldPassword) && newPassword.equals(repeatPassword)){
				user.setPassword(bc.encode(newPassword));
			}
			usersDao.update(user);
		}

		return user;
	}
	
	public User findUser(String login){
		
		return usersDao.findByLogin(login);
	}
	
	public FilteredCollection<User> getFilteredCollection(Integer page){
		
		int pageInt = page != null ? page - 1 : 0;
		List<User> users = usersDao.getAllUserValues();	
		int incZ = users.size() % PAGE_SIZE != 0 ? 1 : 0;
		int totalPages = users.size() / PAGE_SIZE + incZ;
		int begin = Math.max(1, pageInt - 3);
	    int end = Math.min(begin + 3,  totalPages);
		
		return new FilteredCollection<User>(users.subList(Math.max(pageInt*PAGE_SIZE,0),Math.min(pageInt*PAGE_SIZE + PAGE_SIZE, users.size())),
	    		totalPages, begin, end, pageInt + 1);
	}
}
