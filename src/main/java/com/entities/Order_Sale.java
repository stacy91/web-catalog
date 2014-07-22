package com.entities;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Order_Sale {
	private int goodsId;
	private int userId;
	private boolean isSold;
	private int amount;
	private Date time;
	
	public Order_Sale(){
		
	}
	
	@OneToMany
	@JoinColumn(name="GoodsId")
	public int getGoodsId(){
		return goodsId;
	}	
	public void setGoodsId(int goodsId){
		this.goodsId = goodsId;
	}
	
	@OneToMany
	@JoinColumn(name="UserId")
	public int getUserId(){
		return userId;
	}
	public void setUserId(int userId){
		this.userId = userId;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTime(){
		return time;
	}
	public void setTime(Date time){
		this.time = time;
	}
}
