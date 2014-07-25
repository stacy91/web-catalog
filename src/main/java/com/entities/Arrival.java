package com.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "arrival")
public class Arrival {
	private int id;
	private int goodsId;
	private int userId;
	private int amount;
	private float price;
	private Date time;
	
	public Arrival(){
		
	}
	
	public Arrival(int goodsId,int userId,int amount,float price,
			Date time){
		this.goodsId = goodsId;
		this.userId = userId;
		this.amount = amount;
		this.setPrice(price);
		this.time = time;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="GoodsId",nullable=false)
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	@Column(name="UserId",nullable=false)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="Amount",nullable=false)
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Column(name="Price",nullable=false,precision=2)
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Column(name="Time",nullable=false)
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
