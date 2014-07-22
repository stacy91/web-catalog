package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "Goods")
public class Device {

	private int id;
	
	@ManyToOne
	@JoinColumn(name="ModelId")
	private int modelId;
	
	@ManyToOne
	@JoinColumn(name="MemoryId")
	private int memoryId;
	
	@ManyToOne
	@JoinColumn(name="ColorId")
	private int colorId;
	
	private String imageURL;
	private float price;
	private int amountInStock;
	
	public Device(){
		
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public int getId(){
		return id;
	}	
	public void setId(int id){
		this.id = id;
	}
	
	public int getModelId(){
		return modelId;
	}
	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	public int getMemoryId(){
		return memoryId;
	}
	public void setMemoryId(int memoryId){
		this.memoryId = memoryId;
	}
	
	public int getColorId(){
		return colorId;
	}
	public void setColorId(int colorId){
		this.colorId = colorId;
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
