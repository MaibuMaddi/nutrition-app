package com.nutritionix.Authentication.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutritionix.Authentication.exception.AuthenticationException;
import com.nutritionix.Authentication.model.UserCredentials;
import com.nutritionix.Authentication.model.UserProfile;
import com.nutritionix.Authentication.repository.UserProfileRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticationService {
	String secretkey ="secret";
	@Autowired
	UserProfileRepository userProfileRepository;

	
	public UserProfile findByUsername(String username)  {
		return userProfileRepository.findByUsername(username);
	}
	
	public Map<String, String> generateToken(UserCredentials userCredentials) {
//      String jwtToken = "";
      Map<String, String> jwtTokenMap = new HashMap<>();
      Map<String, Object> claims = new HashMap<>();
      String token = Jwts.builder().setClaims(claims).setSubject(userCredentials.getUsername())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))// token for 60 min
              .signWith(SignatureAlgorithm.HS256, secretkey).compact();
           jwtTokenMap.put("token",token);
           jwtTokenMap.put("message","Login Successful");

      return jwtTokenMap;
  	
  }
	
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
//			return false;
			throw new AuthenticationException("Invalid or expired token");
		}
	}
	
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final var claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
	}
	
//	public UserProfile findByUsernameAndPassword(String UserId, String password)
//			throws Exception
//		{
//		UserProfile authUser = userProfileRepository.findByUsernameAndPassword(UserId, password);
//		if (authUser == null) {
//		    throw new Exception();
//		}
//		return authUser;
//
//	}
//	
	
}
