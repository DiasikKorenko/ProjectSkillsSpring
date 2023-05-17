package com.tms.service;

import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransportService {

    TransportRepository transportRepository;

    @Autowired
    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public Transport getTransportById(int id) {
        return transportRepository.findById(id).orElse(null);
    }

    public Transport createTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    public Transport updateTransport(Transport transport) {
        return transportRepository.saveAndFlush(transport);
    }

    @Transactional
    public void deleteTransport(int id) {
        transportRepository.deleteById(id);
    }

    public ArrayList<Transport> getAllTransport() {
        return (ArrayList<Transport>) transportRepository.findAll();
    }
   /* public Transport getTransportById(int id) {
        return transportRepository.getTransportById(id);
    }

    public boolean createTransport(Transport transport) {
        return transportRepository.createTransport(transport);
    }

























    public boolean updateTransport(String typeTransport, int weightTransport, int volumeTransport, int id, int userId) {
        return transportRepository.updateTransport(typeTransport, weightTransport, volumeTransport, id, userId);
    }

    public boolean deleteTransport(int id, int userId) {
        return transportRepository.deleteTransport(id, userId);
    }

    public List<Transport> getUserTransportById(int userId) {
        return transportRepository.getUserTransportById(userId);
    }

    public List<Transport> getAllTransports() {
        return transportRepository.getAllTransports();
    }*/

    public List<Transport> getCurrentUserTransports(Integer userId) {
        List<Transport> transports = transportRepository.findAllByUserId(userId);
        return transports;
    }

    @Transactional
    public boolean deleteCurrentUserTransport(Integer transportId, Integer userId) {
        Transport transportDBEntity = transportRepository.findById(transportId).orElse(null);
        if (transportDBEntity == null || transportDBEntity.getUserId() != userId)
            return false;
        transportRepository.deleteById(transportId);
        transportRepository.flush();
        return true;
    }

    public void currentUserCreateTransport(Integer userId, Transport transport) {
        transport.setUserId(userId);
        transportRepository.saveAndFlush(transport);
    }
}

