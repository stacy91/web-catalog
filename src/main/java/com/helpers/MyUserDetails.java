package com.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.entities.dto.UserDto;



public class MyUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	public  class MyAuthority implements GrantedAuthority{
		
		private static final long serialVersionUID = 1L;
		private String role;
		
		public MyAuthority(String role){
			this.role = role;
		}
		
		@Override
		public String getAuthority() {
			
			return role;
		}
		
	}
	
	private List<GrantedAuthority> authorities;
	private String password;
	private String login;
	
	public MyUserDetails(UserDto user) {
		
		this.password = user.getPassword();
		this.login = user.getLogin();
		String role = user.getRole().getName();
		authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new MyAuthority(role));
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}
	
	
	
	@Override
	public String getPassword() {

		return this.password;
	}

	@Override
	public String getUsername() {

		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
