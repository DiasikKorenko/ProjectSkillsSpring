package com.tms.mapper;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import com.tms.domain.response.CargoResponse;
import com.tms.domain.response.TransportResponse;
import org.springframework.stereotype.Component;

@Component
public class CargoToCargoResponseMapper {

    public CargoResponse cargoToResponse(Cargo cargo) {
        CargoResponse cargoResponse = new CargoResponse();
        cargoResponse.setWeightCargo(cargo.getWeightCargo());
        cargoResponse.setWidthCargo(cargo.getWidthCargo());
        cargoResponse.setLenghtCargo(cargo.getLenghtCargo());
        cargoResponse.setHight(cargo.getHight());
        cargoResponse.setStates(cargo.getStates());
        cargoResponse.setRoute(cargo.getRoute());
        return cargoResponse;
    }
}
