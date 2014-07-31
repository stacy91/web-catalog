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


@Entity
@Table(name = "goods_screenSize")
public class Device_ScreenSize {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="ScreenSize",nullable=false)
	private String screenSize;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="ScreenSizeId")
	private List<Device> devices;
	
	public Device_ScreenSize(){
		
	}
	public Device_ScreenSize(String screenSize){
		this.setScreenSize(screenSize);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}
	
	public List<Device> getDevice() {
		return devices;
	}
	public void setDevice(List<Device> devices) {
		this.devices = devices;
	}
}
