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
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="ModelName",nullable=false)
	private String modelName;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BrandId")
	private Brand brand;
	
	public Device_Model(){
		
	}
	
	public Device_Model(Brand brand,String modelName){
		this.brand = brand;
		this.modelName = modelName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	
	public Brand getBrand(){
		return this.brand;
	}
	
	public void setBrand(Brand brand){
		this.brand = brand;
	}
}
