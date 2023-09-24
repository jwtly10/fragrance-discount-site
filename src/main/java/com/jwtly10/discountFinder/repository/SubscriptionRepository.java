package com.jwtly10.discountFinder.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jwtly10.discountFinder.models.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer>{
    List<Subscription> findByEmail(String email);
}
