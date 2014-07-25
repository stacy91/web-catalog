package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "goods")
public class Device {

	private int id;
	private int modelId;
	private int screenSizeId;
	private int memoryId;
	private int colorId;
	private String imageURL;
	private float price;
	private int amountInStock;
	
	public Device(){
		
	}
	
	public Device(int modelId,int screenSizeId,int memoryId,
			int colorId,String imageURL,float price,
			int amountInStock){
		this.modelId = modelId;
		this.screenSizeId = screenSizeId;
		this.memoryId = memoryId;
		this.colorId = colorId;
		this.imageURL = imageURL;
		this.price = price;
		this.amountInStock = amountInStock;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId(){
		return id;
	}	
	public void setId(int id){
		this.id = id;
	}
	
	@Column(name="ModelId",nullable=false)
	public int getModelId(){
		return modelId;
	}
	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	@Column(name="ScreenSizeId",nullable=false)
	public int getScreenSizeId() {
		return screenSizeId;
	}
	public void setScreenSizeId(int screenSizeId) {
		this.screenSizeId = screenSizeId;
	}
	
	@Column(name="MemoryId",nullable=false)
	public int getMemoryId(){
		return memoryId;
	}
	public void setMemoryId(int memoryId){
		this.memoryId = memoryId;
	}
	
	@Column(name="ColorId",nullable=false)
	public int getColorId(){
		return colorId;
	}
	public void setColorId(int colorId){
		this.colorId = colorId;
	}
	
	@Column(name="ImageURL")
	public String getImageURL(){
		return imageURL;
	}
	public void setImageURL(String imageURL){
		this.imageURL = imageURL;
	}
	
	@Column(name="Price",nullable=false)
	public float getPrice(){
		return price;
	}
	public void setPrice(float price){
		this.price = price;
	}
	
	@Column(name="AmountInStock",nullable=false)
	public int getAmountInStock(){
		return amountInStock;
	}
	public void setAmountInStock(int amountInStock){
		this.amountInStock = amountInStock;
	}

}
