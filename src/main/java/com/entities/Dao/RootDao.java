package com.entities.Dao;

import java.util.List;

public interface RootDao<T> {

	public T create(T item);
	public T update(T item);
	public void delete(int id);
	public List<T> getAll();
	public T find(int id);

}
