package com.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders_sales")
public class Order_Sale {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GoodsId")
	private Device device;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId")
	private User user;
	
	@Column(name="IsSold",nullable=false)
	private boolean isSold;
	
	@Column(name="Amount",nullable=false)
	private int amount;
	
	@Column(name="TimeOrdered")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeOrdered;
	
	@Column(name="TimeSold")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeSold;
	
	public Order_Sale(){
		
	}
	
	public Order_Sale(Device device, User user, boolean isSold,
			int amount, Date timeOrdered, Date timeSold){
		
		this.device = device;
		this.user = user;
		this.isSold = isSold;
		this.amount = amount;
		this.setTimeOrdered(timeOrdered);
		this.setTimeSold(timeSold);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}	
		
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean getIsSold(){
		return isSold;
	}
	public void setIsSold(boolean isSold){
		this.isSold = isSold;
	}
	
	
	public int getAmount(){
		return amount;
	}
	public void setAmount(int amount){
		this.amount = amount;
	}

	public Date getTimeOrdered() {
		return timeOrdered;
	}
	public void setTimeOrdered(Date timeOrdered) {
		this.timeOrdered = timeOrdered;
	}

	public Date getTimeSold() {
		return timeSold;
	}
	public void setTimeSold(Date timeSold) {
		this.timeSold = timeSold;
	}


}
