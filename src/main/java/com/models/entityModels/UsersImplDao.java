package com.models.entityModels;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UsersDao;
import com.entities.User;

@Repository
@Transactional
public class UsersImplDao 	extends RootModel
							implements UsersDao {

	@Autowired
	public UsersImplDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public void create(User user) {
		currentSession().save(user);
	}

	@Override
	public void update(User user) {
		currentSession().update(user);
	}

	@Override
	public void delete(int id) {
		currentSession().delete(this.findById(id));
	}

	@Override
	public void delete(User user) {
		currentSession().delete(user);
	}

	@Override
	public User findById(int id) {
		return (User)currentSession().get(User.class, id);
	}

	@Override
	public User initProxy(User user) {
		User u = this.findById(user.getId());
		Hibernate.initialize(u.getRole());
		return u;
	}

	@Override
	public User initProxy(int id) {
		User u = this.findById(id);
		Hibernate.initialize(u.getRole());
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserValues() {
		return currentSession().createCriteria(User.class).list();
	}

}
