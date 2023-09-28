package com.jwtly10.discountFinder.controllers;

import com.jwtly10.discountFinder.exceptions.ApiError;
import com.jwtly10.discountFinder.exceptions.StoreServiceException;
import com.jwtly10.discountFinder.models.Gender;
import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.models.Sort;
import com.jwtly10.discountFinder.service.SortService;
import com.jwtly10.discountFinder.service.ThePerfumeShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    @Value("${theperfumeshop.api.url}")
    private String thePerfumeShopApiUrl;

    @Autowired
    private ThePerfumeShopService thePerfumeShopService;

    // eg http://localhost:8080/api/v1/stores/theperfumeshop/mens?sort=max_discount
    @GetMapping("/theperfumeshop/{gender}")
    public ResponseEntity<List<Product>> getThePerfumeShopProducts(@PathVariable("gender") Gender gender, @RequestParam(required = false, defaultValue = Sort.Default.DEFAULT_SORT) Sort sort) {
        return ResponseEntity.ok(SortService.sort(thePerfumeShopService.getDiscountedProducts(thePerfumeShopApiUrl, gender), sort));
    }

    @ExceptionHandler(StoreServiceException.class)
    ResponseEntity<ApiError> handleExternalAPIException(StoreServiceException e) {
        return ResponseEntity.internalServerError().body(new ApiError(e.getMessage()));
    }
}
