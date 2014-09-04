package com.entities.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.helpers.FilteredCollection;

public interface RootService<T> {
	
	
	
	public T create(T item) throws DataIntegrityViolationException;
	public T update(T item) throws DataIntegrityViolationException;
	public void delete(int id) throws DataIntegrityViolationException;
	public T find(int id);
	public FilteredCollection<T> getFiltered(List<T> items, Integer page);
	public List<T> getAll();
	
}
