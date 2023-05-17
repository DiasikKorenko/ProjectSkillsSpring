package com.tms.repository;

import com.tms.domain.Subscription;
import com.tms.domain.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {
    Optional<Subscription> findByUserId(Integer userId);
}