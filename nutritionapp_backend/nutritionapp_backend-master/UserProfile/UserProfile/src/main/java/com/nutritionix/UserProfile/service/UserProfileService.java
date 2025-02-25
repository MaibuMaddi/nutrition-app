	package com.nutritionix.UserProfile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutritionix.UserProfile.exception.UsernameAlreadyExistsException;
import com.nutritionix.UserProfile.exception.UsernameAndPasswordNotNullException;
import com.nutritionix.UserProfile.model.UserProfile;
import com.nutritionix.UserProfile.repository.UserProfileRepository;

@Service
public class UserProfileService {
	
	@Autowired
    private UserProfileRepository userProfileRepository;
	@Transactional
    public UserProfile registerUser(UserProfile user) throws Exception {

    	if(user.getUsername()==null || user.getPassword()==null) {
    		throw new UsernameAndPasswordNotNullException("UserName and password should not be Empty");
    	}else {
    		Optional<UserProfile> userFound = userProfileRepository.findByUsername(user.getUsername());
            if(userFound.isEmpty()){
                return userProfileRepository.save(user);
            }
            else{
                throw new UsernameAlreadyExistsException("Username Already exists");
            }
    	}
    }

}
