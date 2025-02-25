package com.nutritionix.UserProfile.controller;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutritionix.UserProfile.custonresponse.CustomResponse;
import com.nutritionix.UserProfile.exception.UsernameAlreadyExistsException;
import com.nutritionix.UserProfile.exception.UsernameAndPasswordNotNullException;
import com.nutritionix.UserProfile.model.UserProfile;
import com.nutritionix.UserProfile.service.UserProfileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userProfile")
@CrossOrigin(origins = "*")
@Slf4j
public class UserProfileController {
	@Autowired
    private UserProfileService userProfileService;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	@Autowired
	private NewTopic topic;
	
	
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserProfile user) throws Exception {
    	try {
//    		log.info("User Registration Started");
	        userProfileService.registerUser(user);
	        kafkaTemplate.send(topic.name(),"This User Registered Successfully "+user.toString());
	        return new ResponseEntity<String>("User registered successfully",HttpStatus.CREATED);
    	}catch(UsernameAlreadyExistsException e) {
    		return CustomResponse.generateResponse("Error while registering User", HttpStatus.BAD_REQUEST,(Object) e.getMessage());
    	}catch(UsernameAndPasswordNotNullException e) {
    		return CustomResponse.generateResponse("Error while registering User", HttpStatus.BAD_REQUEST,(Object) e.getMessage());
    	}
    }
}
