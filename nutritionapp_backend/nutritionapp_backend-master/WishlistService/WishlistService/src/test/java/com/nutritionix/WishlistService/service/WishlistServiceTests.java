package com.nutritionix.WishlistService.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nutritionix.WishlistService.exception.DuplicateProductException;
import com.nutritionix.WishlistService.exception.UserWishlistNotFoundException;
import com.nutritionix.WishlistService.model.BrandedProduct;
import com.nutritionix.WishlistService.model.UserWishlist;
import com.nutritionix.WishlistService.repository.WishListRepository;
import com.nutritionix.WishlistService.service.WishlistService;

@SpringBootTest
class WishlistServiceTest {

    @Mock
    private WishListRepository wishListRepository;

    @InjectMocks
    private WishlistService wishlistService;

    @Test
    void testAddProductToWishlist() throws DuplicateProductException {
       
        String username = "testUser";
        BrandedProduct product = new BrandedProduct();
        product.setNix_item_id("123");

        UserWishlist existingWishlist = new UserWishlist(username, new ArrayList<>());
        when(wishListRepository.findById(username)).thenReturn(Optional.of(existingWishlist));
        when(wishListRepository.save(any(UserWishlist.class))).thenReturn(existingWishlist);

       
        List<BrandedProduct> result = wishlistService.addProdctToWishList(product, username);

        
        assertEquals(1, result.size());
        assertEquals(product.getNix_item_id(), result.get(0).getNix_item_id());
    }

    @Test
    void testAddDuplicateProductToWishlist() {
        
        String username = "testUser";
        BrandedProduct product = new BrandedProduct();
        product.setNix_item_id("123");

        UserWishlist existingWishlist = new UserWishlist(username, List.of(product));
        when(wishListRepository.findById(username)).thenReturn(Optional.of(existingWishlist));

        
        assertThrows(DuplicateProductException.class, () -> wishlistService.addProdctToWishList(product, username));
    }

    @Test
    void testGetUserProducts() throws UserWishlistNotFoundException {
        
        String username = "testUser";
        BrandedProduct product = new BrandedProduct();
        product.setNix_item_id("123");

        UserWishlist existingWishlist = new UserWishlist(username, List.of(product));
        when(wishListRepository.findById(username)).thenReturn(Optional.of(existingWishlist));

        
        List<BrandedProduct> result = wishlistService.getUserProducts(username);

        
        assertEquals(1, result.size());
        assertEquals(product.getNix_item_id(), result.get(0).getNix_item_id());
    }

    @Test
    void testGetUserProductsNotFound() {
        
        String username = "nonExistentUser";
        when(wishListRepository.findById(username)).thenReturn(Optional.empty());

        
        assertThrows(UserWishlistNotFoundException.class, () -> wishlistService.getUserProducts(username));
    }

//    @Test
//    void testDeleteWishlistProduct() throws UserWishlistNotFoundException  {
//        
//        String username = "testUser";
//        String itemId = "123";
//        BrandedProduct product = new BrandedProduct();
//        product.setNix_item_id(itemId);
//
//        UserWishlist existingWishlist = new UserWishlist(username, List.of(product));
//        when(wishListRepository.findById(username)).thenReturn(Optional.of(existingWishlist));
//        when(wishListRepository.save(any(UserWishlist.class))).thenReturn(existingWishlist);
//
//        
//        List<BrandedProduct> result = wishlistService.deleteWishListProduct(itemId, username);
//
//        
//        assertTrue(result.isEmpty());
//    }

    @Test
    void testDeleteWishlistProductNotFound() {
        
        String username = "testUser";
        String itemId = "nonExistentItem";

        when(wishListRepository.findById(username)).thenReturn(Optional.empty());

        
        assertThrows(UserWishlistNotFoundException.class, () -> wishlistService.deleteWishListProduct(itemId, username));
    }

//    @Test
//    void testDeleteWishlistProductNotInWishlist() throws UserWishlistNotFoundException  {
//        
//        String username = "testUser";
//        String itemId = "123";
//        BrandedProduct product = new BrandedProduct();
//        product.setNix_item_id("456");  // Different item ID
//
//        UserWishlist existingWishlist = new UserWishlist(username, List.of(product));
//        when(wishListRepository.findById(username)).thenReturn(Optional.of(existingWishlist));
//        when(wishListRepository.save(any(UserWishlist.class))).thenReturn(existingWishlist);
//
//        
//        List<BrandedProduct> result = wishlistService.deleteWishListProduct(itemId, username);
//
//        
//        assertEquals(1, result.size());
//        assertEquals(product.getNix_item_id(), result.get(0).getNix_item_id());
//    }
}
