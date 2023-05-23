package com.tms.repository;

import com.tms.domain.Transport;
import com.tms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport,Integer> {
    List<Transport> findTransportByUserId (Integer id);
}
