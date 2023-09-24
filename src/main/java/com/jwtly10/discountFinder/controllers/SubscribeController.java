package com.jwtly10.discountFinder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtly10.discountFinder.exceptions.ApiError;
import com.jwtly10.discountFinder.exceptions.SubscriptionServiceException;
import com.jwtly10.discountFinder.service.SubscribeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @PostMapping("/subscribe/{email}")
    public ResponseEntity<?> subscribe(@PathVariable String email) {
        return ResponseEntity.ok(subscribeService.subscribe(email));
    }

    @ExceptionHandler(SubscriptionServiceException.class)
    ResponseEntity<ApiError> handleServiceException(SubscriptionServiceException e) {
        return ResponseEntity.internalServerError().body(new ApiError(e.getMessage()));
    }
}
