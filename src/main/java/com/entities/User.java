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
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RoleId")
	private UserRole role;
	@Column(name="Login",nullable=false)
	private String login;
	@Column(name="Password",nullable=false)
	private String password;
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="UserId")
	private List<Arrival> arrivals;
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="UserId")
	private List<Order_Sale> orders_sales;
	
	public User(){
		
	}
	
	public User(UserRole role, String login, String password){
		this.role = role;
		this.login = login;
		this.password = password;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
		
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	
}
