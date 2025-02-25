package com.nutritionix.WishlistService.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="authentication-service",url = "localhost:8082/")
public interface AuthClient {
	
	@GetMapping("auth/validateToken")
    public boolean validateToken(@RequestHeader(name = "Authorization") String token);
	
	@GetMapping("auth/getUsername")
	public String getUsername(@RequestHeader(name = "Authorization") String token);

}
