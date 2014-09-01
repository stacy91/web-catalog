package com.entities.dto;

import java.util.Date;
import com.entities.Order_Sale;


public class Order_SaleDto {

	private int id;
	private DeviceDto device;
	private UserDto user;
	private boolean isSold;
	private int amount;
	private Date timeOrdered;
	private Date timeSold;
	
	public Order_SaleDto(){
		
	}
	
	public Order_SaleDto(Order_Sale o_s){	
		if (o_s != null) {
			this.id = o_s.getId();
			this.device = new DeviceDto(o_s.getDevice());
			this.user = new UserDto(o_s.getUser());
			this.isSold = o_s.getIsSold();
			this.amount = o_s.getAmount();
			this.timeOrdered = o_s.getTimeOrdered();
			this.timeSold = o_s.getTimeSold();
		}
	}
	
	public Order_Sale getEntity(){
		
		return new Order_Sale(this.id,
				this.device == null ? null : this.device.getEntity(),
				this.user == null ? null : this.user.getEntity(),
				this.isSold,this.amount,this.timeOrdered,this.timeSold);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public DeviceDto getDevice() {
		return device;
	}
	public void setDevice(DeviceDto device) {
		this.device = device;
	}	
		
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
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
