package com.jwtly10.discountFinder.controllers;

import com.jwtly10.discountFinder.exceptions.ApiError;
import com.jwtly10.discountFinder.exceptions.SortServiceException;
import com.jwtly10.discountFinder.exceptions.StoreServiceException;
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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StoreController {

    @Value("${theperfumeshop.api.url}")
    private String thePerfumeShopApiUrl;

    @Autowired
    private ThePerfumeShopService thePerfumeShopService;

    // eg http://localhost:8080/api/v1/stores/theperfumeshop/mens?sort=max_discount
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/products/{gender}")
    public ResponseEntity<List<Product>> getThePerfumeShopProducts(@PathVariable("gender") String gender, @RequestParam(required = false, defaultValue = Sort.Default.DEFAULT_SORT) String sort) {
        return ResponseEntity.ok(SortService.sort(thePerfumeShopService.getDiscountedProducts(thePerfumeShopApiUrl, gender), sort));
    }

    @ExceptionHandler(StoreServiceException.class)
    ResponseEntity<ApiError> handleExternalAPIException(StoreServiceException e) {
        return ResponseEntity.internalServerError().body(new ApiError(e.getMessage()));
    }

    @ExceptionHandler(SortServiceException.class)
    ResponseEntity<ApiError> handleSortServiceException(SortServiceException e) {
        return ResponseEntity.badRequest().body(new ApiError(e.getMessage()));
    }
}
