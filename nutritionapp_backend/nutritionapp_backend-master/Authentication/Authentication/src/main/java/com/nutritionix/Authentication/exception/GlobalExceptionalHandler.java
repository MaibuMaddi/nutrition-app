package com.nutritionix.Authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionalHandler {
	
	@ExceptionHandler(UserCredentialsNullException.class)
	public ResponseEntity<String> usernameOrPasswordNull(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(UserCredentialsMisMatch.class)
	public ResponseEntity<String> usernameAndPasswordNotMatch(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> InvalidToken(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
	@ExceptionHandler(TokenGeneratingException.class)
	public ResponseEntity<String> tokenNotGenerated(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }

}
