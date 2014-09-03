package com.entities.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.User;
import com.entities.Dao.UserRolesDao;
import com.entities.Dao.UsersDao;
import com.entities.dto.UserDto;
import com.entities.dto.UserRoleDto;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

@Service
@Transactional
public class UsersService {
	
	@Autowired 
	private UsersDao usersDao;
	@Autowired 
	private UserRolesDao rolesDao;
	private final int PAGE_SIZE = 10;
	
	public UserDto create(UserDto user){

		String encodedPas = user.getPassword();
		encodedPas = new BCryptPasswordEncoder().encode(encodedPas);
		user.setPassword(encodedPas);
		user.setRole(new UserRoleDto(rolesDao.find(4)));
		User entity = user.getEntity();
		usersDao.create(entity); 
		return user;
	}
	
	public void changeRole(int id, String login){
		
		User user = usersDao.find(id);
		if(!user.getLogin().equals(login)){
			if(user.getRole().getId() == 3)
				user.setRole(rolesDao.find(4));
			else
				user.setRole(rolesDao.find(3));
			usersDao.update(user);
		}
	}
	
	public void delete(int id){
		usersDao.delete(id);
	}
	
	public UserDto update(UserDto user,String oldPassword,String newPassword,String repeatPassword){
		
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String encodedOldPas = bc.encode(oldPassword);
		if(oldPassword != null && newPassword != null & repeatPassword != null){
			if(encodedOldPas.equals(oldPassword) && newPassword.equals(repeatPassword)){
				user.setPassword(bc.encode(newPassword));
			}
			usersDao.update(user.getEntity());
		}

		return user;
	}
	
	public UserDto findUser(String login){
		
		return new UserDto(usersDao.findByLogin(login));
	}
	
	public FilteredCollection<UserDto> getFilteredCollection(Integer page){
		
		List<UserDto> users = new ArrayList<UserDto>();
		
		for(User item : usersDao.getAll()){
			users.add(new UserDto(item));
		}
		return FilteredCollectionGenerator.getFilteredCollection(page, PAGE_SIZE, users);
	}
}
