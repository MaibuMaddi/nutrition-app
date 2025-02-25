package com.nutritionix.Authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutritionix.Authentication.model.UserProfile;



@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	UserProfile findByUsernameAndPassword(String username, String password) ;
	UserProfile findByUsername(String username) ;
}

