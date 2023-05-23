package com.tms.mapper;

import com.tms.domain.Transport;
import com.tms.domain.response.TransportResponse;
import org.springframework.stereotype.Component;

@Component
public class TransportToTransportResponseMapper {

    public TransportResponse transportToResponse(Transport transport) {
        TransportResponse transportResponse = new TransportResponse();
        transportResponse.setTypeTransport(transport.getTypeTransport());
        transportResponse.setVolumeTransport(transport.getVolumeTransport());
        transportResponse.setWeightTransport(transport.getWeightTransport());
        return transportResponse;
    }
}
