package com.nutritionix.UserProfile.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nutritionix.UserProfile.exception.UsernameAlreadyExistsException;
import com.nutritionix.UserProfile.exception.UsernameAndPasswordNotNullException;
import com.nutritionix.UserProfile.model.UserProfile;
import com.nutritionix.UserProfile.repository.UserProfileRepository;
import com.nutritionix.UserProfile.service.UserProfileService;

@SpringBootTest
class UserProfileServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private UserProfileService userProfileService;

    @Test
    void testRegisterUserSuccess() throws Exception {
        UserProfile user = new UserProfile();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        when(userProfileRepository.findByUsername("testUser")).thenReturn(Optional.empty());
        when(userProfileRepository.save(user)).thenReturn(user);

        UserProfile registeredUser = userProfileService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals("testUser", registeredUser.getUsername());
        assertEquals("testPassword", registeredUser.getPassword());

        verify(userProfileRepository, times(1)).findByUsername("testUser");
        verify(userProfileRepository, times(1)).save(user);
    }

    @Test
    void testRegisterUserUsernameAlreadyExists() {
        UserProfile user = new UserProfile();
        user.setUsername("existingUser");
        user.setPassword("testPassword");

        when(userProfileRepository.findByUsername("existingUser")).thenReturn(Optional.of(user));

        assertThrows(UsernameAlreadyExistsException.class, () -> {
            userProfileService.registerUser(user);
        });

        verify(userProfileRepository, times(1)).findByUsername("existingUser");
        verify(userProfileRepository, never()).save(any(UserProfile.class));
    }

    @Test
    void testRegisterUserUsernameAndPasswordNull() {
        UserProfile user = new UserProfile();

        assertThrows(UsernameAndPasswordNotNullException.class, () -> {
            userProfileService.registerUser(user);
        });

        
        verify(userProfileRepository, never()).save(any(UserProfile.class));
    }
}
