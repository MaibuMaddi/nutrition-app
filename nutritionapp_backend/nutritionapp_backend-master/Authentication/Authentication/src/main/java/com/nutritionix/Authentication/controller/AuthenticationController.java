package com.nutritionix.Authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nutritionix.Authentication.customresponse.CustomResponse;
import com.nutritionix.Authentication.exception.UserCredentialsMisMatch;
import com.nutritionix.Authentication.exception.UserCredentialsNullException;
import com.nutritionix.Authentication.model.UserCredentials;
import com.nutritionix.Authentication.model.UserProfile;
import com.nutritionix.Authentication.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserCredentials userCredentials) {
        try {
//        	log.info("User trying to logging in");
            validateUserCredentials(userCredentials);
            validateUserPassword(userCredentials);

            return new ResponseEntity<>(authService.generateToken(userCredentials), HttpStatus.OK);

        } catch (Exception e) {
//            return new ResponseEntity<>("check credentials", HttpStatus.CONFLICT);
        	return CustomResponse.generateResponse("Login Error - Check Credentials", HttpStatus.FORBIDDEN,(Object) e.getMessage());
        }
    }

    @GetMapping("/validateToken")
    public boolean validateUser(@RequestHeader("Authorization") String token) {
//    	log.info("validating token",token);
        return authService.validateToken(token);
    }

    @GetMapping("/getUsername")
    public String getUsername(@RequestHeader("Authorization") String token) {
//    	log.info("Extracting username from token",token);
    	String response = authService.extractUsername(token);
    	System.out.println(response);
        return authService.extractUsername(token);
    }

    private void validateUserCredentials(UserCredentials userCredentials) {
//    	log.info("Validating User Credentials is null");
        if (userCredentials.getUsername() == null || userCredentials.getPassword() == null) {
            throw new UserCredentialsNullException("Username and password cannot be null");
        }
    }

    private void validateUserPassword(UserCredentials userCredentials) {
        UserProfile userProfile = authService.findByUsername(userCredentials.getUsername());
        if (!userCredentials.getPassword().equals(userProfile.getPassword())) {
            throw new UserCredentialsMisMatch("Invalid Username or Password");
        }
    }
}



















//package com.nutritionix.Authentication.controller;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nutritionix.Authentication.exception.UserCredentialsMisMatch;
//import com.nutritionix.Authentication.exception.UserCredentialsNullException;
//import com.nutritionix.Authentication.model.UserCredentials;
//import com.nutritionix.Authentication.model.UserProfile;
//import com.nutritionix.Authentication.service.AuthenticationService;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthenticationController {
//	
//	@Autowired
//	AuthenticationService authService;
//	
//	@PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody UserCredentials userCredentials){
//		System.out.println(authService.findByUsername(userCredentials.getUsername()));
//        try {
//            if (userCredentials.getUsername() == null || userCredentials.getPassword() == null) {
//                throw new UserCredentialsNullException("username and password cannot be null");
//            }
//            	UserProfile userProfile = authService.findByUsername(userCredentials.getUsername());
//            if (!(userCredentials.getPassword().equals(userProfile.getPassword()))) {
//                throw new UserCredentialsMisMatch("Invalid Password");
//            }
//            Map<String,String> token=authService.generateToken(userCredentials);
//            System.out.println(token);
//            return new ResponseEntity<>(token,HttpStatus.OK);
//            
//        } catch (Exception e) {
////            responseEntity = 
//            	return	new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
////        return responseEntity;
//    }
//	
//	@GetMapping("/validateToken")
//	public boolean ValiditeUser(@RequestHeader("Authorization")String token) {
//		
//		return authService.validateToken(token);
//	}
//	
//	@GetMapping("/getUsername")
//	public String getUsername(@RequestHeader("Authorization")  String token) {
//		
//		return authService.extractUsername(token);
//	}
//
//}
