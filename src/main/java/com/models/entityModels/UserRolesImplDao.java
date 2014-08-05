package com.models.entityModels;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserRolesDao;
import com.entities.UserRole;

@Repository
@Transactional
public class UserRolesImplDao 	extends RootModel
								implements UserRolesDao {
	
	@Autowired
	public UserRolesImplDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public UserRole findById(int id) {
		
		return (UserRole)currentSession().get(UserRole.class, id);
	}

	@Override
	public UserRole initProxy(UserRole ur) {
		UserRole urr = findById(ur.getId());
		Hibernate.initialize(urr);
		urr.getUsers().size();
		return urr;
	}

	@Override
	public UserRole initProxy(int id) {
		UserRole urr = findById(id);
		Hibernate.initialize(urr);	
		urr.getUsers().size();
		return urr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getAllUserRoleValues() {
		return currentSession().createCriteria(UserRole.class).list();
	}

}
