package com.nutritionix.WishlistService.controller;

import com.nutritionix.WishlistService.feignClient.AuthClient;
import com.nutritionix.WishlistService.model.BrandedProduct;
import com.nutritionix.WishlistService.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WishListControllerTest {

    @Mock
    private AuthClient authClient;

    @Mock
    private WishlistService wishListService;

    @InjectMocks
    private WishListController wishListController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProductToWishList() throws Exception {
        
        BrandedProduct product = new BrandedProduct();
        product.setNix_item_id("item123");
        String token = "validToken";
        String username = "user123";
        List<BrandedProduct> expectedProducts = Arrays.asList(product);

        when(authClient.validateToken(token)).thenReturn(true);
        when(authClient.getUsername(token)).thenReturn(username);
        when(wishListService.addProdctToWishList(product, username)).thenReturn(expectedProducts);

        
        ResponseEntity<?> response = wishListController.addProductToWishList(product, token);

        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedProducts, response.getBody());
    }

    @Test
    void testGetUserWishList() throws Exception {
        
        String token = "validToken";
        String username = "user123";
        List<BrandedProduct> expectedProducts = Arrays.asList(new BrandedProduct());

        when(authClient.validateToken(token)).thenReturn(true);
        when(authClient.getUsername(token)).thenReturn(username);
        when(wishListService.getUserProducts(username)).thenReturn(expectedProducts);

        
        ResponseEntity<?> response = wishListController.getUserWishList(token);

        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedProducts, response.getBody());
    }

    @Test
    void testDeleteUserProduct() throws Exception {
        
        String itemId = "item123";
        String token = "validToken";
        String username = "user123";
        List<BrandedProduct> expectedProducts = Arrays.asList();

        when(authClient.validateToken(token)).thenReturn(true);
        when(authClient.getUsername(token)).thenReturn(username);
        when(wishListService.deleteWishListProduct(itemId, username)).thenReturn(expectedProducts);

        
        ResponseEntity<?> response = wishListController.deleteUserProduct(itemId, token);

        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedProducts, response.getBody());
    }

}

