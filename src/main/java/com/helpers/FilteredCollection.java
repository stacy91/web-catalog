package com.helpers;

import java.util.List;


public class FilteredCollection<T> {
	
	private List<T> items;
	private int totalPages;
	private int begin;
	private int end;
	private int currentPage;
	
	public FilteredCollection(List<T> items,int totalPages, int begin, int end, int currentPage){
		this.items = items;
		this.totalPages = totalPages;
		this.begin = begin;
		this.end = end;
		this.currentPage = currentPage;
	}

	public List<T> getItems() {
		return items;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public int getCurrentPage() {
		return currentPage;
	}

}
