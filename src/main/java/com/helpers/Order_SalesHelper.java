package com.helpers;

import java.util.ArrayList;
import java.util.List;
import com.entities.Order_Sale;

public class Order_SalesHelper {

	public static List<Order_Sale> getSales(List<Order_Sale> os) {

		return filter(os,true);
	}

	public static List<Order_Sale> getOrders(List<Order_Sale> os) {
		
		return filter(os,false);
	}
	
	private static List<Order_Sale> filter(List<Order_Sale> os, boolean isSold){
		
		List<Order_Sale> newList = new ArrayList<Order_Sale>();
		for(Order_Sale item : os){
			if(item.getIsSold() == isSold)
				newList.add(item);
		}
		
		return newList;
	}
}
