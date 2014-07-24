package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Goods_Color")
public class Device_Color {
	
	private int id;
	private String color;
	
	public Device_Color(){
		
	}
	
	public Device_Color(String color){
		this.setColor(color);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="Color",nullable=false)
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
