package com.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "brands")
public class Brand {
	
	private int id;
	private String brandName;
	private List<Device_Model> deviceModels;
	
	public Brand(){
		
	}
	
	public Brand(String brandName){
		this.brandName = brandName;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="BrandName",nullable=false)
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
	public List<Device_Model> getDeviceModels(){
		return this.deviceModels;
	}
	
	public void setDeviceModels(List<Device_Model> dms){
		this.deviceModels = dms;
	}
}
