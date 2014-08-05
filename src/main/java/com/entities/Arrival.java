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

@Entity
@Table(name = "arrival")
public class Arrival {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GoodsId")
	private Device device;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="UserId")
	private User user;
	@Column(name="Amount",nullable=false)
	private int amount;
	@Column(name="Price",nullable=false,precision=2)
	private float price;
	@Column(name="Time",nullable=false)
	private Date time;
	
	public Arrival(){
		
	}
	
	public Arrival(Device device,User user,int amount,float price,
			Date time){
		this.setDevice(device);
		this.setUser(user);
		this.amount = amount;
		this.price = price;
		this.time = time;
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
