package com.tms.service;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.domain.response.TransportResponse;
import com.tms.exception.BadRequestEx;
import com.tms.exception.ForbiddenEx;
import com.tms.exception.NotFoundEx;
import com.tms.mapper.TransportToTransportResponseMapper;
import com.tms.repository.TransportRepository;
import com.tms.repository.UserRepository;
import com.tms.security.CheckingAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransportService {

    private final TransportRepository transportRepository;
    private final TransportToTransportResponseMapper transportToTransportResponseMapper;
    private final CheckingAuthorization checkingAuthorization;

    private final UserRepository userRepository;

    @Autowired
    public TransportService(TransportRepository transportRepository, TransportToTransportResponseMapper transportToTransportResponseMapper, CheckingAuthorization checkingAuthorization, UserRepository userRepository) {
        this.transportRepository = transportRepository;
        this.transportToTransportResponseMapper = transportToTransportResponseMapper;
        this.checkingAuthorization = checkingAuthorization;
        this.userRepository = userRepository;
    }

    public TransportResponse getTransportResponseById(int id) {
        Optional<Transport> selectedTransport = transportRepository.findById(id);
        if (selectedTransport.isPresent()) {
            return transportToTransportResponseMapper.transportToResponse(selectedTransport.get());
        } else {
            throw new NotFoundEx("Transport is not found");
        }
    }

    public List<TransportResponse> getAllTransportResponse() {
        List<TransportResponse> transport = transportRepository.findAll().stream()
                .map(transportToTransportResponseMapper::transportToResponse)
                .collect(Collectors.toList());
        if (!transport.isEmpty()) {
            return transport;
        } else {
            throw new NotFoundEx("No transport");
        }
    }

    public void createTransport(@Valid Transport transport) {
        Optional<User> selectedUser = userRepository.findById(transport.getUserId());
        if (selectedUser.isPresent()) {
            transport.setUserEmail(selectedUser.get().getEmail());
            transportRepository.save(transport);
        }else {
            throw new NotFoundEx("User is not found");
        }
    }

    public void updateTransport(Transport transport) {
        Transport gotTransport = transportRepository.findById(transport.getId()).orElseThrow(() -> new NotFoundEx("Transport is not found"));
        if (checkingAuthorization.check(gotTransport.getUserEmail())) {
            transportRepository.saveAndFlush(transport);
        } else {
            throw new ForbiddenEx("You can't update not your transport");
        }
    }

    @Transactional
    public void deleteTransport(int id) {
        Optional<Transport> selectedTransport = transportRepository.findById(id);
        if (selectedTransport.isPresent()) {
            if (checkingAuthorization.check(getUserEmail(id))) {
                transportRepository.deleteById(id);
            } else {
                throw new ForbiddenEx("You can't delete not your transport");
            }
        } else {
            throw new NotFoundEx("Transport is not found");
        }
    }

    private String getUserEmail(int id) {
        return transportRepository.findById(id).get().getUserEmail();
    }


    public List<TransportResponse> findTransportResponseByUserId(int userId) {
        List<TransportResponse> transportse = transportRepository.findTransportByUserId(userId).stream()
                .map(transportToTransportResponseMapper::transportToResponse)
                .collect(Collectors.toList());
        if (!transportse.isEmpty()) {
            return transportse;
        } else {
            throw new NotFoundEx("There are no information from this user");
        }
    }



    public Transport getTransportById(int id) {
        Optional<Transport> selectedTransport = transportRepository.findById(id);
        if (selectedTransport.isPresent()) {
            return selectedTransport.get();
        } else {
            throw new NotFoundEx("Transport is not found");
        }
    }

    public List<Transport> getAllTransport() {
        List<Transport> transport = transportRepository.findAll();
        if (!transport.isEmpty()) {
            return transport;
        } else {
            throw new NotFoundEx("There are no information transport");
        }
    }

    public List<Transport> findTransportByUserId(int userId) {
        List<Transport> transport = transportRepository.findTransportByUserId(userId);
        if (!transport.isEmpty()) {
            return transport;
        } else {
            throw new NotFoundEx("There are no information from this user");
        }
    }

    @Transactional
    public void deleteTransportByAdmin(int id) {
        transportRepository.findById(id).orElseThrow(() -> new NotFoundEx("Transport is not found"));
    }

    public void updateTransportAdmin(Transport transport) {
        transportRepository.findById(transport.getId()).orElseThrow(() -> new NotFoundEx("Transport is not found"));
        transportRepository.saveAndFlush(transport);
    }
}

