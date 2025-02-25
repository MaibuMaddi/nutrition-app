package com.nutritionix.WishlistService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutritionix.WishlistService.customResponse.CustomResponse;
import com.nutritionix.WishlistService.feignClient.AuthClient;
import com.nutritionix.WishlistService.model.BrandedProduct;
import com.nutritionix.WishlistService.service.WishlistService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/wish")
@CrossOrigin(origins = "*")
@Slf4j
public class WishListController {
	
	@Autowired 
	AuthClient authClient;
	@Autowired 
	WishlistService wishListService;
	
	private void validateToken(String token) throws Exception {
//		log.info("Calling validating token in Authentication..."+token);
		try {
			if (authClient.validateToken(token)==false || !authClient.validateToken(token)) throw new Exception();
		} catch (Exception e) {
			throw new Exception("feign Client Exception"+e);
		}
	}
	
	public String  getUsername(String token) {
//		log.info("extractig user name from token : "+token);
		return authClient.getUsername(token);
	}
//	@GetMapping("/test")
//	public String test(@RequestHeader(name = "Authorization", required = true) String token) throws Exception {
////		System.out.println(token);
////		validateToken(token);
////		return "testing";
//		return authClient.getUsername(token);
//	}
	
	@PostMapping("/addItem")
	public ResponseEntity<?> addProductToWishList(
			@RequestBody BrandedProduct product,
			@RequestHeader(name = "Authorization", required = true) String token) throws Exception{
		try {
//			log.info("addig item to wishlist",product);
			validateToken(token);
			String username=getUsername(token);
//			log.debug("Extracting Username",username);
			 List<BrandedProduct> products = wishListService.addProdctToWishList(product,username);
			return new ResponseEntity<List<BrandedProduct>>(products, HttpStatus.CREATED);
		}catch (Exception e) {
			return CustomResponse.generateResponse("Error in Adding product to wishList", HttpStatus.FORBIDDEN,(Object) e.getMessage());
		}
	}
	
	
	@GetMapping("/getUserWishList")
	public ResponseEntity<?> getUserWishList(
			@RequestHeader(name = "Authorization", required = true) String token) throws Exception{
		try {
//			log.info("getting user wishlist");
			validateToken(token);
			String username=getUsername(token);
//			log.debug("Extracting Username",username);
			 List<BrandedProduct> products = wishListService.getUserProducts(username);
			return new ResponseEntity<List<BrandedProduct>>(products, HttpStatus.CREATED);
		}catch(Exception e) {
			return CustomResponse.generateResponse("Error in Retreiving products from wishList", HttpStatus.BAD_REQUEST,(Object) e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteUserProduct/{itemId}")
	public ResponseEntity<?> deleteUserProduct(
			@PathVariable String itemId,
			@RequestHeader(name = "Authorization", required = true) String token) throws Exception{
		try {
//			log.info("deleting user from wishlist with id "+itemId);
			validateToken(token);
			String username=getUsername(token);
//			log.debug("Extracting Username",username);
			 List<BrandedProduct> products = wishListService.deleteWishListProduct(itemId, username);
			return new ResponseEntity<List<BrandedProduct>>(products, HttpStatus.CREATED);
		}catch(Exception e) {
			return CustomResponse.generateResponse("Error in Deleting product from wishList", HttpStatus.BAD_REQUEST,(Object) e.getMessage());
		}
	}
	
	
	

}
