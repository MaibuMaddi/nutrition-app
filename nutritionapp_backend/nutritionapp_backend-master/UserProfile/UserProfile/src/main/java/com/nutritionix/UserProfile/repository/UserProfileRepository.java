package com.nutritionix.UserProfile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nutritionix.UserProfile.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

	Optional<UserProfile> findByUsername(String username);
	
	@Query("SELECT u FROM UserProfile u WHERE u.username = :username")
    Optional<UserProfile> getByUserName(@Param("username") String username);
}
