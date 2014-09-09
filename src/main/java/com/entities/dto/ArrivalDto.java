package com.entities.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.entities.Arrival;


public class ArrivalDto {

	private int id;
	private DeviceDto device;
	private UserDto user;
	@NotNull
	@Min(1)
	@Max(999999)
	private int amount;
	@NotNull
	@Min(0)
	@Max(999999)
	private float price;
	private Date time;
	
	public ArrivalDto(){
		
	}
	
	public ArrivalDto(Arrival arrival) {
		
		if (arrival != null) {
			this.id = arrival.getId();
			this.device = new DeviceDto(arrival.getDevice());
			this.user = new UserDto(arrival.getUser());
			this.amount = arrival.getAmount();
			this.price = arrival.getPrice();
			this.time = arrival.getTime();
		}
	}
	
	public Arrival getEntity(){
		
		return new Arrival(this.id, this.device == null ? null : this.device.getEntity(),
				this.user == null ? null : this.user.getEntity(),
						this.amount, this.price, this.time);
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
		
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
		
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
