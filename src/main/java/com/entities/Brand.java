package com.entities;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "brands")
public class Brand {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Length(min=2,max=10,message="Length should be between 2 and 10 symbols")
	
	@Column(name="BrandName",nullable=false)
	private String brandName;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="BrandId")
	private List<Device> devices;
	
	
	public Brand(){
		
	}
	
	public Brand(String brandName){
		this.brandName = brandName;
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
	
	
	public List<Device> getDevices(){
		return this.devices;
	}
	
	public void setDevices(List<Device> devices){
		this.devices = devices;
	}
}
