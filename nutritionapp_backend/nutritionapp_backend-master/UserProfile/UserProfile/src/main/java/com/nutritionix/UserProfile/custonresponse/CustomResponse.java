package com.nutritionix.UserProfile.custonresponse;



import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse {
	
	public static ResponseEntity<?> generateResponse(String message, HttpStatus status, Object responseObj) {
    	Map<String, Object> map = new HashMap<>();
    	map.put(message,responseObj);
        return new ResponseEntity<>(map,status);
    }

	


}
