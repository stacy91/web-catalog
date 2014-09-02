package com.entities.ImplDao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.entities.Dao.RootDao;



@Transactional
@SuppressWarnings("unchecked")
public abstract class RootDaoImpl<T> implements RootDao<T>{
	

	protected Class<T> classT;
	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@SuppressWarnings("rawtypes")
	public RootDaoImpl(){
		Type t = getClass().getGenericSuperclass();
	     ParameterizedType pt = (ParameterizedType) t;
	     classT = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public T create(T item) {
		currentSession().save(item);
		return item;
	}

	@Override
	public T update(T item){
		currentSession().update(item);
		return item;
	}

	@Override
	public void delete(int id) {
		currentSession().delete(find(id));
	}


	@Override
	public T find(int id) {
		
		return (T)currentSession().get(classT,id);
	}
	
	@Override
	public List<T> getAll() {
		return currentSession().createCriteria(classT).list();
	}
}
