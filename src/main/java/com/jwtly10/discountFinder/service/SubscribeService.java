package com.jwtly10.discountFinder.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtly10.discountFinder.exceptions.SubscriptionServiceException;
import com.jwtly10.discountFinder.models.Subscription;
import com.jwtly10.discountFinder.repository.SubscriptionRepository;
import com.jwtly10.discountFinder.utils.Validate;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class SubscribeService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public String subscribe(String email) {

        if (Validate.isEmailValid(email)) {
            log.info("Email is not valid");
            throw new SubscriptionServiceException("Email is not valid");
        }

        if (!checkEmailExists(email)) {
            log.info("Email already exists");
            throw new SubscriptionServiceException("Email Already Exists");
        }

        Subscription subscription = Subscription.builder()
                .email(email)
                .subscribed(true)
                .created(new Timestamp(System.currentTimeMillis()))
                .updated(new Timestamp(System.currentTimeMillis()))
                .build();

        try {
            subscriptionRepository.save(subscription);
        } catch (Exception e){
            log.error("Error while saving subscription: {}", e.getMessage(), e);
            throw new SubscriptionServiceException("Error while saving subscription");
        }

        log.info("Subscription saved successfully");
        return email + " - Has been subscribed successfully";
    }

    private boolean checkEmailExists(String email) {
        List<Subscription> sub = subscriptionRepository.findByEmail(email);
        return !sub.isEmpty();
    }
}
