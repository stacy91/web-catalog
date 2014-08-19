package com.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "goods")
public class Device {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BrandId")
	private Brand brand;
	@Column(name="Model",nullable=false,unique=true)
	private String model;
	@Column(name="Price",nullable=false)
	private float price;
	@Column(name="AmountInStock",nullable=false)
	private int amountInStock;
	@Column(name="HasImage",nullable=false)
	private boolean hasImage;
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="GoodsId")
	private List<Arrival> arrivals;
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="GoodsId")
	private List<Order_Sale> orders_sales;
	
	
	public Device(){
		
	}
	
	public Device(Brand brand,String model,float price,
			int amountInStock){
		this.brand = brand;
		this.setModel(model);
		this.price = price;
		this.amountInStock = amountInStock;
	}
	
	
	public int getId(){
		return id;
	}	
	public void setId(int id){
		this.id = id;
	}
	
	public Brand getBrand(){
		return brand;
	}
	public void setBrand(Brand brand){
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

	public List<Arrival> getArrivals() {
		return arrivals;
	}
	public void setArrivals(List<Arrival> arrivals) {
		this.arrivals = arrivals;
	}

	public List<Order_Sale> getOrders_Sales() {
		return orders_sales;
	}
	public void setOrders_Sales(List<Order_Sale> orders_sales) {
		this.orders_sales = orders_sales;
	}

	public boolean isHasImage() {
		return hasImage;
	}

	public void setHasImage(boolean hasImage) {
		this.hasImage = hasImage;
	}

}
