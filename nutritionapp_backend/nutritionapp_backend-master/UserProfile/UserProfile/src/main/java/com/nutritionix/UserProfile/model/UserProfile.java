package com.nutritionix.UserProfile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long UserId;
	String username;
	String password;
	String email;
	public Long getUserId() {
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserProfile(Long userId, String username, String password, String email) {
		super();
		UserId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public UserProfile() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserProfile [UserId=" + UserId + ", username=" + username + ", password=" + password + ", email="
				+ email + "]";
	}
	
	
	

}
