package com.tms.service;

import com.tms.domain.Subscription;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.repository.SubscriptionRepository;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.*;

@Service
public class SubscriptionService {
    SubscriptionRepository subscriptionRepository;

    UserRepository userRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
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





}
/*
    public boolean updateSubscription( int id,Date expireDate, int userId) {
       return subscriptionRepository.updateSubscription(id,expireDate,userId);
    }

    public boolean deleteSubscription(int id) {
        return subscriptionRepository.deleteSubscription(id);
    }

    public boolean addSubscription(int userId) {
        return subscriptionRepository.addSubscription(userId);
    }
}*/
