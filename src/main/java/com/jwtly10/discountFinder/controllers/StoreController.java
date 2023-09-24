package com.jwtly10.discountFinder.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtly10.discountFinder.exceptions.ApiError;
import com.jwtly10.discountFinder.exceptions.StoreServiceException;
import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.service.ThePerfumeShopService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    @Autowired
    private ThePerfumeShopService thePerfumeShopService;

    @GetMapping("/theperfumeshop/{gender}")
    public ResponseEntity<List<Product>> getThePerfumeShopProducts(@PathVariable String gender) {
        return ResponseEntity.ok(thePerfumeShopService.getDiscountedProducts(gender));
    }

    @ExceptionHandler(StoreServiceException.class)
    ResponseEntity<ApiError> handleExternalAPIException(StoreServiceException e) {
        System.out.println("does this happen");
        return ResponseEntity.internalServerError().body(new ApiError(e.getMessage()));
    }
}
