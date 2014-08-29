package com.helpers;

import java.util.List;

import org.springframework.ui.ModelMap;


public class FilteredCollectionGenerator {

	static public <T> FilteredCollection<T>  getFilteredCollection(Integer page,int pageSize, List<T> items){
		
		int pageInt = page != null ? page - 1 : 0;
		
		int incZ = items.size() % pageSize != 0 ? 1 : 0;
		int totalPages = items.size() / pageSize + incZ;
		
		if(totalPages == pageInt){
			pageInt--;
		}
		int begin = Math.max(1, pageInt - 3);
	    int end = Math.min(begin + 3,  totalPages);
		
		return new FilteredCollection<T>(items.subList(Math.max(pageInt*pageSize,0),Math.min(pageInt*pageSize + pageSize, items.size())),
	    		totalPages, begin, end, pageInt + 1);
	}
	
	static public  <T> void fillPagination(ModelMap model,FilteredCollection<T> fCollection){
		
		model.addAttribute("beginIndex", fCollection.getBegin());
	    model.addAttribute("endIndex", fCollection.getEnd());
	    model.addAttribute("currentIndex", fCollection.getCurrentPage());
		model.addAttribute("totalPages",fCollection.getTotalPages());
	}
}
