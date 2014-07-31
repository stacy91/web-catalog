package com.entities;

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
@Table(name = "goods")
public class Device {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ModelId")
	private Device_Model model;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ScreenSizeId")
	private Device_ScreenSize screenSize;
	@Column(name="ImageURL")
	private String imageURL;
	@Column(name="Price",nullable=false)
	private float price;
	@Column(name="AmountInStock",nullable=false)
	private int amountInStock;
	
	public Device(){
		
	}
	
	public Device(Device_Model model,Device_ScreenSize screenSize,String imageURL,float price,
			int amountInStock){
		this.model = model;
		this.screenSize = screenSize;
		this.imageURL = imageURL;
		this.price = price;
		this.amountInStock = amountInStock;
	}
	
	
	public int getId(){
		return id;
	}	
	public void setId(int id){
		this.id = id;
	}
	
	public Device_Model getModel(){
		return model;
	}
	public void setModel(Device_Model model){
		this.model = model;
	}
	
	public Device_ScreenSize getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(Device_ScreenSize screenSize) {
		this.screenSize = screenSize;
	}
	
	
	public String getImageURL(){
		return imageURL;
	}
	public void setImageURL(String imageURL){
		this.imageURL = imageURL;
	}
	
	
	public float getPrice(){
		return price;
	}
	public void setPrice(float price){
		this.price = price;
	}
		
	public int getAmountInStock(){
		return amountInStock;
	}
	public void setAmountInStock(int amountInStock){
		this.amountInStock = amountInStock;
	}

}
