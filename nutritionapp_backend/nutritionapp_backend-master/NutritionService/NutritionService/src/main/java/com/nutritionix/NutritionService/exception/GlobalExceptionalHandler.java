package com.nutritionix.NutritionService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionalHandler {
	@ExceptionHandler(DataFetchingNutritionUrlException.class)
    public ResponseEntity<String> wrongUrl(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
