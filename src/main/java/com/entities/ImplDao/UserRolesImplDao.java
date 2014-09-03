package com.entities.ImplDao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.entities.UserRole;
import com.entities.Dao.UserRolesDao;

@Component
@Transactional
public class UserRolesImplDao 	extends RootDaoImpl<UserRole>
								implements UserRolesDao {
	

	

	@Override
	public UserRole initUsers(int id) {
		UserRole urr = find(id);
		Hibernate.initialize(urr);	
		urr.getUsers().size();
		return urr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> getAll() {
		return currentSession().createCriteria(UserRole.class)
				.addOrder(Order.asc("name")).list();
	}

}
