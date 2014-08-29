package com.entities.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.entities.User;
import com.entities.Dao.UserRolesDao;
import com.entities.Dao.UsersDao;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

@Service
public class UsersService {
	
	@Autowired 
	private UsersDao usersDao;
	@Autowired 
	private UserRolesDao rolesDao;
	private final int PAGE_SIZE = 10;
	
	public User create(User user){
		String encodedPas = user.getPassword();
		encodedPas = new BCryptPasswordEncoder().encode(encodedPas);
		user.setPassword(encodedPas);
		user.setRole(rolesDao.findById(4));
		usersDao.create(user);
		return user;
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
		
		List<User> users = usersDao.getAllUserValues();	
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, users);
	}
}
