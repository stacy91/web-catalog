package com.helpers;

import java.util.List;


public class FilteredCollectionGenerator {

	static public <T> FilteredCollection<T>  getFilteredCollection(int page,int pageSize, List<T> items){
		
		int incZ = items.size() % pageSize != 0 ? 1 : 0;
		int totalPages = items.size() / pageSize + incZ;
		
		if(totalPages == page){
			page--;
		}
		int begin = Math.max(1, page - 3);
	    int end = Math.min(begin + 3,  totalPages);
		
		return new FilteredCollection<T>(items.subList(Math.max(page*pageSize,0),Math.min(page*pageSize + pageSize, items.size())),
	    		totalPages, begin, end, page + 1);
	}
}
