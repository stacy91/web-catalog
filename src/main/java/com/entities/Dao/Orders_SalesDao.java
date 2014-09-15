package com.entities.Dao;

import com.entities.Order_Sale;

public interface Orders_SalesDao extends RootDao<Order_Sale>{
	
	public Order_Sale initOrder_Sale(int id);
}
