package com.tms.service;

import com.tms.domain.Cargo;
import com.tms.domain.response.CargoResponse;
import com.tms.mapper.CargoToCargoResponseMapper;
import com.tms.domain.User;
import com.tms.repository.CargoRepository;
import com.tms.repository.UserRepository;
import com.tms.security.CheckingAuthorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CargoServiceTest {
    @InjectMocks
    private CargoService cargoService;
    @Mock
    private CargoRepository cargoRepository;
    @Mock
    private CargoToCargoResponseMapper cargoToCargoResponseMapper;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private CheckingAuthorization checkingAuthorization;
    @Mock
    private UserRepository userRepository;
    private int id;
    private Cargo cargo;
    @Mock
    private User user;
    private CargoResponse cargoResponse;
    private final List<Cargo> cargos = new ArrayList<>();
    private final List<CargoResponse> cargoResponses = new ArrayList<>();

    @BeforeEach
    public void init() {
        id = 1;
        cargo = new Cargo();
        cargo.setId(1);
        cargo.setWeightCargo("123");
        cargo.setWidthCargo("123");
        cargo.setLenghtCargo("123");
        cargo.setHight("123");
        cargo.setStates("testStates");
        cargo.setRoute("testRout");
        cargo.setUserId(1);
        cargo.setUserEmail("test@gmail.com");
        cargos.add(cargo);
        cargoResponse = new CargoResponse();
        cargoResponses.add(cargoResponse);
    }

    @Test
    public void getCargoResponseByIdTest() {
        when(cargoRepository.findById(id)).thenReturn(Optional.of(cargo));
        when(cargoToCargoResponseMapper.cargoToResponse(cargo)).thenReturn(cargoResponse);
        CargoResponse returned = cargoService.getCargoResponseById(id);
        verify(cargoRepository).findById(id);
        verify(cargoToCargoResponseMapper).cargoToResponse(cargo);
        assertEquals(cargoResponse, returned);
    }

    @Test
    public void getAllCargoResponseTest() {
        when(cargoRepository.findAll()).thenReturn(cargos);
        when(cargoToCargoResponseMapper.cargoToResponse(cargo)).thenReturn(cargoResponse);
        assertEquals(cargoResponses, cargoService.getAllCargoResponse());
    }

    @Test
    public void findCargoResponseByUserIdTest() {
        when(cargoRepository.findCargoByUserId(id)).thenReturn(cargos);
        when(cargoToCargoResponseMapper.cargoToResponse(cargo)).thenReturn(cargoResponse);
        assertEquals(cargoResponses, cargoService.findCargoResponseByUserId(id));
    }

    @Test
    public void createCargoTest() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        cargoService.createCargo(cargo);
        verify(cargoRepository).save(cargo);
    }

    @Test
    public void updateCargoTest() {
        when(cargoRepository.findById(id)).thenReturn(Optional.of(cargo));
        when(checkingAuthorization.check(cargo.getUserEmail())).thenReturn(true);
        cargoService.updateCargo(cargo);
        verify(cargoRepository).saveAndFlush(cargo);
    }

    @Test
    public void deleteTransportTest() {
        when(cargoRepository.findById(id)).thenReturn(Optional.of(cargo));
        when(checkingAuthorization.check(cargo.getUserEmail())).thenReturn(true);
        cargoService.deleteCargo(id);
        verify(cargoRepository).deleteById(id);
    }


}