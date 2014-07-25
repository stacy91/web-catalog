package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userroles")
public class UserRole {
	
	private int id;
	private String role;
	
	public UserRole(){
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="Role")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}	
}
