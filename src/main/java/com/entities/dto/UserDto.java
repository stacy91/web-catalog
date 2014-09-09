package com.entities.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.entities.User;


public class UserDto {
	
	private int id;
	private UserRoleDto role;
	
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	@Size(min=3,max=15)
	private String login;
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	@Size(min=4,max=15)
	private String password;

	private String confirmPass;
	
	@SuppressWarnings("unused")
	private boolean valid;
	
	public UserDto(){
		
	}
	
	public UserDto(User user){
		if(user != null){
			this.id = user.getId();
			this.role = new UserRoleDto(user.getRole());
			this.login = user.getLogin();
			this.password = user.getPassword();
			this.confirmPass = this.password;
		}
	}
	
	public User getEntity(){
		return new User(this.id,this.role == null ? null : this.role.getEntity(),
				this.login,this.password);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public UserRoleDto getRole() {
		return role;
	}
	public void setRole(UserRoleDto role) {
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
	
	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
	@AssertTrue(message="AssertTrue.user.isValid")
	  private boolean isValid() {
		
		
	    return this.password.equals(this.confirmPass);
	    
	  }
	
	public boolean equals(Object obj) {
		 boolean result = false;
	       if (!(obj instanceof UserDto))
	            return result;
	       UserDto user = (UserDto)obj;
	       if(this.getId() == user.getId())
	    	   result = true;
	       return result;
	 }

	
}
