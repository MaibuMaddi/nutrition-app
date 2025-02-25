package com.nutritionix.WishlistService.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nutritionix.WishlistService.model.UserWishlist;

@Repository
public interface WishListRepository extends MongoRepository<UserWishlist, String>{

}
