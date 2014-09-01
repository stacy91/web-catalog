package com.entities.dto;

import com.entities.UserRole;



public class UserRoleDto {
	

	private int id;
	private String name;

	
	public UserRoleDto(){
		
	}
	public UserRoleDto(UserRole userRole){
		if (userRole != null) {
			this.id = userRole.getId();
			this.name = userRole.getName();
		}
	}
	
	public UserRole getEntity(){
		
		return new UserRole(this.id,this.name);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
