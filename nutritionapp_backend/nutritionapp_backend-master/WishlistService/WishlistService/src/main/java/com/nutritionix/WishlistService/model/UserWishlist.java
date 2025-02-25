package com.nutritionix.WishlistService.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "wishlist")
public class UserWishlist {
	@Id
	String username;
	List<BrandedProduct> products;
	public UserWishlist(String username, List<BrandedProduct> products) {
		super();
		this.username = username;
		this.products = products;
	}
	public UserWishlist() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<BrandedProduct> getProducts() {
		return products;
	}
	public void setProducts(List<BrandedProduct> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "UserWishlist [username=" + username + ", products=" + products + "]";
	}

	
}
