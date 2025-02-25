package com.nutritionix.WishlistService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<String> productAlreadyExist(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
	
	@ExceptionHandler(UserWishlistNotFoundException.class)
    public ResponseEntity<String> eserWishlistNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
