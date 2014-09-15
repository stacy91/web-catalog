package com.entities.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.entities.Device;



public class DeviceDto {

	private int id;
	
	@NotNull
	private BrandDto brand;
	
	@Size(min=2,max=10)
	private String model;
	
	@NotNull
	@Min(0)
	@Max(999999)
	private float price;

	private int amountInStock;

	
	
	public DeviceDto(){
		
	}
	
	public DeviceDto(Device device) {
		if (device != null) {
			this.id = device.getId();
			this.brand = new BrandDto(device.getBrand());
			this.model = device.getModel();
			this.price = device.getPrice();
			this.amountInStock = device.getAmountInStock();
		}
	}
	
	public int getId(){
		return id;
	}	
	public void setId(int id){
		this.id = id;
	}
	
	public BrandDto getBrand(){
		return brand;
	}
	public void setBrand(BrandDto brand){
		this.brand = brand;
	}	
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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

	
	public boolean equals(Object obj) {
		 boolean result = false;
	       if (!(obj instanceof DeviceDto))
	            return result;
	       DeviceDto device = (DeviceDto)obj;
	       if(this.getId() == device.getId())
	    	   result = true;
	       return result;
	 }

}
