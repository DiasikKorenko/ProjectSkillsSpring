package com.tms.service;

import com.tms.domain.Transport;
import com.tms.domain.response.TransportResponse;
import com.tms.mapper.TransportToTransportResponseMapper;
import com.tms.domain.User;
import com.tms.repository.TransportRepository;
import com.tms.repository.UserRepository;
import com.tms.security.CheckingAuthorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransportServiceTest {

    @InjectMocks
    private TransportService transportService;
    @Mock
    private TransportRepository transportRepository;
    @Mock
    private TransportToTransportResponseMapper transportToTransportResponseMapper;
    @Mock
    private CheckingAuthorization checkingAuthorization;
    @Mock
    private UserRepository userRepository;
    private int id;
    private Transport transport;
    @Mock
    private User user;
    private TransportResponse transportResponse;
    private final List<Transport> transportes = new ArrayList<>();
    private final List<TransportResponse> transportResponses = new ArrayList<>();

    @BeforeEach
    public void init() {
        id = 1;
        transport = new Transport();
        transport.setId(1);
        transport.setTypeTransport("TestType");
        transport.setWeightTransport("123");
        transport.setVolumeTransport("123");
        transport.setUserId(1);
        transport.setUserEmail("test@gmail.com");
        transportes.add(transport);
        transportResponse = new TransportResponse();
        transportResponses.add(transportResponse);
    }

    @Test
    public void getTransportResponseByIdTest() {
        when(transportRepository.findById(id)).thenReturn(Optional.of(transport));
        when(transportToTransportResponseMapper.transportToResponse(transport)).thenReturn(transportResponse);
        TransportResponse returned = transportService.getTransportResponseById(id);
        verify(transportRepository).findById(id);
        verify(transportToTransportResponseMapper).transportToResponse(transport);
        assertEquals(transportResponse, returned);
    }

    @Test
    public void getAllTransportResponseTest() {
        when(transportRepository.findAll()).thenReturn(transportes);
        when(transportToTransportResponseMapper.transportToResponse(transport)).thenReturn(transportResponse);
        assertEquals(transportResponses, transportService.getAllTransportResponse());
    }

    @Test
    public void findTransportResponseByUserIdTest() {
        when(transportRepository.findTransportByUserId(id)).thenReturn(transportes);
        when(transportToTransportResponseMapper.transportToResponse(transport)).thenReturn(transportResponse);
        assertEquals(transportResponses, transportService.findTransportResponseByUserId(id));
    }

    @Test
    public void createTransportTest() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        transportService.createTransport(transport);
        verify(transportRepository).save(transport);
    }

    @Test
    public void updateTransportTest() {
        when(transportRepository.findById(id)).thenReturn(Optional.of(transport));
        when(checkingAuthorization.check(transport.getUserEmail())).thenReturn(true);
        transportService.updateTransport(transport);
        verify(transportRepository).saveAndFlush(transport);
    }

    @Test
    public void deleteTransportTest() {
        when(transportRepository.findById(id)).thenReturn(Optional.of(transport));
        when(checkingAuthorization.check(transport.getUserEmail())).thenReturn(true);
        transportService.deleteTransport(id);
        verify(transportRepository).deleteById(id);
    }
}


