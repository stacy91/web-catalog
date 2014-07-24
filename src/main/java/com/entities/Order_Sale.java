package com.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Orders_Sales")
public class Order_Sale {
	private int id;
	private int goodsId;
	private int userId;
	private boolean isSold;
	private int amount;
	private Date time;
	
	public Order_Sale(){
		
	}
	
	public Order_Sale(int goodsId, int userId, boolean isSold,
			int amount, Date time){
		this.goodsId = goodsId;
		this.userId = userId;
		this.isSold = isSold;
		this.amount = amount;
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
	public int getGoodsId(){
		return goodsId;
	}	
	public void setGoodsId(int goodsId){
		this.goodsId = goodsId;
	}
	
	@Column(name="UserId",nullable=false)
	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	@Column(name="IsSold",nullable=false)
	public boolean getIsSold(){
		return isSold;
	}
	public void setIsSold(boolean isSold){
		this.isSold = isSold;
	}
	
	@Column(name="Amount",nullable=false)
	public int getAmount(){
		return amount;
	}
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	@Column(name="Time",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTime(){
		return time;
	}
	public void setTime(Date time){
		this.time = time;
	}
}
