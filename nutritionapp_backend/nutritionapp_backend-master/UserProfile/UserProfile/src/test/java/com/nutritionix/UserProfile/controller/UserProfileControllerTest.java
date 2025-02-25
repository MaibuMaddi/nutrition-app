package com.nutritionix.UserProfile.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import com.nutritionix.UserProfile.model.UserProfile;
import com.nutritionix.UserProfile.service.UserProfileService;
import com.nutritionix.UserProfile.controller.UserProfileController;

@SpringBootTest
class UserProfileControllerTest {

    @Mock
    private UserProfileService userProfileService;


    @InjectMocks
    private UserProfileController userProfileController;

//    @Test
//    void testRegisterUserSuccess() throws Exception {
//        UserProfile user = new UserProfile();
//        user.setUsername("testUser");
//        user.setPassword("testPassword");
//
//        when(userProfileService.registerUser(user)).thenReturn(user);
//
//        ResponseEntity<String> responseEntity = userProfileController.registerUser(user);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals("User registered successfully", responseEntity.getBody());
//
//        verify(userProfileService, times(1)).registerUser(user);
//    }

    @Test
    void testRegisterUserException() throws Exception {
        UserProfile user = new UserProfile();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        when(userProfileService.registerUser(user)).thenThrow(new Exception("Test Exception"));

        assertThrows(Exception.class, () -> {
            userProfileController.registerUser(user);
        });

        verify(userProfileService, times(1)).registerUser(user);
    }
}
