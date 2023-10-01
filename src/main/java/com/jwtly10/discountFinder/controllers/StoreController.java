package com.jwtly10.discountFinder.controllers;

import com.jwtly10.discountFinder.exceptions.ApiError;
import com.jwtly10.discountFinder.exceptions.RateLimitException;
import com.jwtly10.discountFinder.exceptions.SortServiceException;
import com.jwtly10.discountFinder.exceptions.StoreServiceException;
import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.models.Sort;
import com.jwtly10.discountFinder.service.SortService;
import com.jwtly10.discountFinder.service.ThePerfumeShopService;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Log4j2
public class StoreController {

    @Value("${theperfumeshop.api.url}")
    private String thePerfumeShopApiUrl;

    @Autowired
    private ThePerfumeShopService thePerfumeShopService;

    private final Bucket bucket;

    public StoreController(
            @Value("${rateLimit.capacity}") int capacity, 
            @Value("${rateLimit.duration}") int duration, 
            @Value("${rateLimit.refill}") long refill){
        Bandwidth limit = Bandwidth.classic(capacity, 
                Refill.greedy(refill, Duration.ofMinutes(duration)));
        this.bucket = Bucket.builder()
            .addLimit(limit)
            .build();
            }

    @GetMapping("/products/{gender}")
    public ResponseEntity<List<Product>> getThePerfumeShopProducts(@PathVariable("gender") String gender, @RequestParam(required = false, defaultValue = Sort.Default.DEFAULT_SORT) String sort) {
        if (bucket.tryConsume(1)){
            return ResponseEntity.ok(SortService.sort(thePerfumeShopService.getDiscountedProducts(thePerfumeShopApiUrl, gender), sort));
        }

        log.error("Rate limit hit.");
        throw new RateLimitException("Too many requests. Please try again in a few minutes.");
    }

    @ExceptionHandler(RateLimitException.class)
    ResponseEntity<ApiError> handleRateLimitException(RateLimitException e) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new ApiError(e.getMessage()));
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
