package com.tms.repository;

import com.tms.domain.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport,Integer> {
    List<Transport> findTransportByUserId (Integer id);
}
