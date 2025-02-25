package com.nutritionix.Authentication.exception;

public class UserCredentialsMisMatch extends RuntimeException{
	public UserCredentialsMisMatch(String msg) {
		super(msg);
	}

}
