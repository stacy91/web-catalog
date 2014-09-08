package com.entities.services;

import java.util.List;
import com.helpers.FilteredCollection;

public interface RootService<T> {
	
	
	
	public T create(T item);
	public T update(T item);
	public void delete(int id);
	public T find(int id);
	public FilteredCollection<T> getFiltered(List<T> items, Integer page);
	public List<T> getAll();
	
}
