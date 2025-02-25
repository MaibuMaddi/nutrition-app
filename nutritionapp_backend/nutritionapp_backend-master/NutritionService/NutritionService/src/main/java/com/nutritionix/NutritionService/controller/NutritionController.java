package com.nutritionix.NutritionService.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutritionix.NutritionService.model.BrandedProduct;
import com.nutritionix.NutritionService.service.NutritionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/nutrition")
@CrossOrigin(origins = "*")
@Slf4j
public class NutritionController {

	@Autowired
    private NutritionService nutritionService;
	

    @GetMapping("/searchProduct/{query}")
    public ResponseEntity<?> searchProducts(@PathVariable String query) throws Exception {
//    	log.info("Searching products from Nutrition APi with name "+query);
    	
        try {
            List<BrandedProduct> products = nutritionService.searchProducts(query);
//            log.debug("getting products", products);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error while fetching nutrition information", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search/{itemId}")
    public ResponseEntity<?> searchProductByItemId(@PathVariable String itemId) throws Exception {
//    	log.info("getting product from Nutrition Api with itemId "+itemId);
        try {
            BrandedProduct product = nutritionService.getBrandedProduct(itemId);
//            log.debug("getting product", product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while fetching nutrition information", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
