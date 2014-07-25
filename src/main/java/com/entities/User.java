package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {
	
	private int id;
	private int roleId;
	private String login;
	private String password;
	
	public User(){
		
	}
	
	public User(int roleId, String login, String password){
		this.roleId = roleId;
		this.login = login;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="RoleId",nullable=false)
	public int getRole() {
		return roleId;
	}
	public void setRole(int roleId) {
		this.roleId = roleId;
	}
	
	@Column(name="Login",nullable=false)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name="Password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
