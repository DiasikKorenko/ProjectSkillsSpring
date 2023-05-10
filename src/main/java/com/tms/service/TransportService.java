package com.tms.service;

import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TransportService {

    TransportRepository transportRepository;

    @Autowired
    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    /*public Transport getTransportById(int id) {
        return transportRepository.getTransportById(id);
    }*/

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
    }
}
