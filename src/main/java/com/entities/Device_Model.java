package com.entities;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "goods_model")
public class Device_Model {
	
	private int id;
	private int brandId;
	private String modelName;
	private Brand brand;
	
	public Device_Model(){
		
	}
	
	public Device_Model(int brandId,String modelName){
		this.brandId = brandId;
		this.modelName = modelName;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="BrandId",nullable = false)
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	
	@Column(name="ModelName",nullable=false)
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Id")
	public Brand getBrand(){
		return this.brand;
	}
	
	public void setBrand(Brand brand){
		this.brand = brand;
	}
}
