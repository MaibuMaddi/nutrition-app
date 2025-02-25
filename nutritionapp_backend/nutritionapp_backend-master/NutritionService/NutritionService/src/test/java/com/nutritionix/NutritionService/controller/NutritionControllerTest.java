package com.nutritionix.NutritionService.controller;

import com.nutritionix.NutritionService.controller.NutritionController;
import com.nutritionix.NutritionService.model.BrandedProduct;
import com.nutritionix.NutritionService.service.NutritionService;

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
import static org.mockito.Mockito.when;

class NutritionControllerTest {

    @Mock
    private NutritionService nutritionService;

    @InjectMocks
    private NutritionController nutritionController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchProducts() throws Exception {
        // Mocking the service response
        List<BrandedProduct> mockedProducts = Arrays.asList(new BrandedProduct(), new BrandedProduct());
        when(nutritionService.searchProducts("testQuery")).thenReturn(mockedProducts);

        // Testing the controller method
        ResponseEntity<?> responseEntity = nutritionController.searchProducts("testQuery");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof List);
        assertEquals(mockedProducts, responseEntity.getBody());
    }

//    @Test
//    void testSearchProductsException() throws Exception {
//        // Mocking an exception in the service
//        when(nutritionService.searchProducts("testQuery")).thenThrow(new Exception("Test Exception"));
//
//        // Testing the controller method
//        ResponseEntity<?> responseEntity = nutritionController.searchProducts("testQuery");
//
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertTrue(responseEntity.getBody() instanceof String);
//        assertEquals("Error while fetching nutrition information", responseEntity.getBody());
//    }

    @Test
    void testSearchProductByItemId() throws Exception {
        // Mocking the service response
        BrandedProduct mockedProduct = new BrandedProduct();
        when(nutritionService.getBrandedProduct("testItemId")).thenReturn(mockedProduct);

        // Testing the controller method
        ResponseEntity<?> responseEntity = nutritionController.searchProductByItemId("testItemId");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof BrandedProduct);
        assertEquals(mockedProduct, responseEntity.getBody());
    }

    @Test
    void testSearchProductByItemIdException() throws Exception {
        // Mocking an exception in the service
        when(nutritionService.getBrandedProduct("testItemId")).thenThrow(new Exception("Test Exception"));

        // Testing the controller method
        ResponseEntity<?> responseEntity = nutritionController.searchProductByItemId("testItemId");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody() instanceof String);
        assertEquals("Error while fetching nutrition information", responseEntity.getBody());
    }
}
