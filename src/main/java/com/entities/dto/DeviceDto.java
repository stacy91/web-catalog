package com.entities.dto;

import com.entities.Device;



public class DeviceDto {

	private int id;
	private BrandDto brand;
	private String model;
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
	
	public Device getEntity(){
		
		return new Device(this.id,this.brand == null ? null : this.brand.getEntity(),
				this.model,this.price,this.amountInStock);
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
	       if (!(obj instanceof BrandDto))
	            return result;
	       DeviceDto device = (DeviceDto)obj;
	       if(this.getId() == device.getId())
	    	   result = true;
	       return result;
	 }

}
