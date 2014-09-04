package com.entities.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.User;
import com.entities.Dao.UserRolesDao;
import com.entities.Dao.UsersDao;
import com.entities.dto.UserDto;
import com.entities.dto.UserRoleDto;
import com.entities.services.UsersService;
import com.helpers.FilteredCollection;
import com.helpers.FilteredCollectionGenerator;

@Service
@Transactional
public class UsersServiceImpl implements UsersService{
	
	@Autowired 
	private UsersDao usersDao;
	@Autowired 
	private UserRolesDao rolesDao;
	
	@Override
	public UserDto create(UserDto user)
			throws DataIntegrityViolationException{

		String encodedPas = user.getPassword();
		encodedPas = new BCryptPasswordEncoder().encode(encodedPas);
		user.setPassword(encodedPas);
		user.setRole(new UserRoleDto(rolesDao.find(4)));
		User entity = user.getEntity();
		usersDao.create(entity); 
		return user;
	}
	
	@Override
	public UserDto update(UserDto user)
			throws DataIntegrityViolationException{
		
		
		usersDao.update(user.getEntity());

		return user;
	}
	
	@Override
	public void delete(int id)
			throws DataIntegrityViolationException{
		usersDao.delete(id);
	}
	
	@Override
	public UserDto find(String login){
		
		return new UserDto(usersDao.findByLogin(login));
	}
	
	@Override
	public UserDto find(int id) {
		return new UserDto(usersDao.find(id));
	}
	
	@Override
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
	

	@Override
	public FilteredCollection<UserDto> getFiltered(List<UserDto> items,
			Integer page) {
		List<UserDto> users = new ArrayList<UserDto>();

		for (User item : usersDao.getAll()) {
			users.add(new UserDto(item));
		}
		return FilteredCollectionGenerator.getFilteredCollection(page,
				PAGE_SIZE, users);
	}

	@Override
	public List<UserDto> getAll() {
		
		List<UserDto> users = new ArrayList<UserDto>();

		for (User item : usersDao.getAll()) {
			users.add(new UserDto(item));
		}
		
		return (users);
	}

	@Override
	public UserDto validate(UserDto user, String oldPassword, String newPassword,
			String repeatPassword) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String encodedOldPas = bc.encode(oldPassword);
		if(oldPassword != null && newPassword != null & repeatPassword != null){
			if(encodedOldPas.equals(oldPassword) && newPassword.equals(repeatPassword)){
				user.setPassword(bc.encode(newPassword));
				return user;
			}
		}
		return null;
	}
}
