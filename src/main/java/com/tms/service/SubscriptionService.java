package com.tms.service;

import com.tms.domain.Cargo;
import com.tms.domain.Subscription;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.repository.SubscriptionRepository;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.*;

@Service
public class SubscriptionService {
    SubscriptionRepository subscriptionRepository;

    UserRepository userRepository;
    UserService userService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository,UserService userService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    private final long ONE_YEAR = 31556926000L;

    public Subscription getSubscriptionById(int id) {
        return subscriptionRepository.findById(id).orElse(null);
    }


    public Subscription createSubscription(int userId) {
        Subscription subscription = new Subscription();
        if (userRepository.findUserById(userId) != null) {
            subscription.setUserId(userId);
            subscription.setExpireDate(new Date((new java.util.Date()).getTime() + ONE_YEAR));
            return subscriptionRepository.saveAndFlush(subscription);
        } else
            return null;
    }

    public Subscription updateSubscription(Subscription subscription) {
        return subscriptionRepository.saveAndFlush(subscription);
    }

    @Transactional
    public void deleteSubscription(int id) {
        subscriptionRepository.deleteById(id);
    }


    public Subscription getSubscriptionByCurrentUserId(Integer userId) {
        return subscriptionRepository.findByUserId(userId).orElse(null);
    }

    public Subscription createCurrentUserSubscription(int userId) {
        Subscription subscription = new Subscription();
        if (userRepository.findUserById(userId) != null) {
            subscription.setUserId(userId);
            subscription.setExpireDate(new Date((new java.util.Date()).getTime() + ONE_YEAR));
            return subscriptionRepository.saveAndFlush(subscription);
        } else
            return null;
    }

    @Transactional
    public boolean deleteCurrentUserSubscription(Integer id, Integer userId) {
        Subscription subscriptionDBEntity = subscriptionRepository.findById(id).orElse(null);
        subscriptionRepository.deleteById(id);
        if (subscriptionDBEntity == null || subscriptionDBEntity.getUserId() != userId)
            return false;
        subscriptionRepository.deleteById(id);
        subscriptionRepository.flush();
        return true;
    }

}