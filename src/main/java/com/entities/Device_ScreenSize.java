package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "goods_screenSize")
public class Device_ScreenSize {
	
	private int id;
	private String screenSize;
	
	public Device_ScreenSize(){
		
	}
	
	public Device_ScreenSize(String screenSize){
		this.setScreenSize(screenSize);
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="ScreenSize",nullable=false)
	public String getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}
}
