package com.nutritionix.Authentication.model;

import org.springframework.stereotype.Component;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Component
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UserCredentials {
	String username;
	String password;
//	public UserCredentials(String string, String string2) {
//		// TODO Auto-generated constructor stub
//	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserCredentials [username=" + username + ", password=" + password + "]";
	}
	public UserCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
