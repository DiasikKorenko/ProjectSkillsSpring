package com.tms.repository;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo,Integer> {
    List<Cargo> findAllByUserId(Integer id);
}
