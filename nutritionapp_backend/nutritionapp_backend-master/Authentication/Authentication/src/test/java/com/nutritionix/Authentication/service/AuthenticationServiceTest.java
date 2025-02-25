package com.nutritionix.Authentication.service;

import com.nutritionix.Authentication.exception.AuthenticationException;
import com.nutritionix.Authentication.model.UserCredentials;
import com.nutritionix.Authentication.model.UserProfile;
import com.nutritionix.Authentication.repository.UserProfileRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    private static final String SECRET_KEY = "secret";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationService = new AuthenticationService();
        authenticationService.secretkey = SECRET_KEY;
    }

    @Test
    void testGenerateToken() {
        UserCredentials userCredentials = new UserCredentials("testUser", "testPassword");
        Map<String, String> jwtTokenMap = authenticationService.generateToken(userCredentials);

        assertNotNull(jwtTokenMap);
        assertTrue(jwtTokenMap.containsKey("token"));
        assertTrue(jwtTokenMap.containsKey("message"));
        assertEquals("Login Successful", jwtTokenMap.get("message"));

        String token = jwtTokenMap.get("token");
        assertNotNull(token);

        // Validate token
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        assertEquals(userCredentials.getUsername(), claims.getSubject());
    }

    @Test
    void testValidateValidToken() {
        String validToken = createValidToken();
        assertTrue(authenticationService.validateToken(validToken));
    }

    

    @Test
    void testExtractUsername() {
        String validToken = createValidToken();
        String extractedUsername = authenticationService.extractUsername(validToken);
        assertEquals("testUser", extractedUsername);
    }

    @Test
    void testExtractClaim() {
        String validToken = createValidToken();
        String extractedSubject = authenticationService.extractClaim(validToken, Claims::getSubject);
        assertEquals("testUser", extractedSubject);
    }

    private String createValidToken() {
        return Jwts.builder()
                .setSubject("testUser")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
