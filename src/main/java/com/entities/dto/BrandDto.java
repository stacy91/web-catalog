package com.entities.dto;

import org.hibernate.validator.constraints.Length;
import com.entities.Brand;



public class BrandDto {
	

	private int id;
	@Length(min=2,max=10)
	private String brandName;
	
	
	public BrandDto(){
		
	}
	
	public BrandDto(Brand brand) {
		if (brand != null) {
			this.id = brand.getId();
			this.brandName = brand.getBrandName();
		}
	}
	
	public Brand getEntity(){
		
		return new Brand(this.id,this.brandName);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getBrandName() {
		return this.brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}	

	
	 public boolean equals(Object obj) {
		 boolean result = false;
	       if (!(obj instanceof BrandDto))
	            return result;
	       BrandDto brand = (BrandDto)obj;
	       if(this.getId() == brand.getId())
	    	   result = true;
	       return result;
	 }
}