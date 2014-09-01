package com.helpers;

public class AreaChartPoint {

	private int orders;
	private int sales;

	public AreaChartPoint(int orders, int sales) {
		this.setOrders(orders);
		this.setSales(sales);
	}

	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}

	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}

}
