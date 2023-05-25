package com.tms.service;

import com.tms.domain.Cargo;
import com.tms.domain.User;
import com.tms.domain.response.CargoResponse;
import com.tms.exception.ForbiddenEx;
import com.tms.exception.NotFoundEx;
import com.tms.mapper.CargoToCargoResponseMapper;
import com.tms.repository.CargoRepository;
import com.tms.repository.UserRepository;
import com.tms.security.CheckingAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;
    private final CargoToCargoResponseMapper cargoToCargoResponseMapper;
    private final CheckingAuthorization checkingAuthorization;
    private final UserRepository userRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository, CargoToCargoResponseMapper cargoToCargoResponseMapper, CheckingAuthorization checkingAuthorization, UserRepository userRepository) {
        this.cargoRepository = cargoRepository;
        this.cargoToCargoResponseMapper = cargoToCargoResponseMapper;
        this.checkingAuthorization = checkingAuthorization;
        this.userRepository = userRepository;
    }

    public CargoResponse getCargoResponseById(int id) {
        Optional<Cargo> selectedCargo = cargoRepository.findById(id);
        if (selectedCargo.isPresent()) {
            return cargoToCargoResponseMapper.cargoToResponse(selectedCargo.get());
        } else {
            throw new NotFoundEx("Cargo is not found");
        }
    }

    public List<CargoResponse> getAllCargoResponse() {
        List<CargoResponse> cargo = cargoRepository.findAll().stream()
                .map(cargoToCargoResponseMapper::cargoToResponse)
                .collect(Collectors.toList());
        if (!cargo.isEmpty()) {
            return cargo;
        } else {
            throw new NotFoundEx("No cargo");
        }
    }

    public void createCargo(Cargo cargo) {
        Optional<User> selectedUser = userRepository.findById(cargo.getUserId());
        if (selectedUser.isPresent()) {
            cargo.setUserEmail(selectedUser.get().getEmail());
            cargoRepository.save(cargo);
        } else {
            throw new NotFoundEx("User is not found");
        }
    }

    public void updateCargo(Cargo cargo) {
        Optional<Cargo> selectedCargo = cargoRepository.findById(cargo.getId());
        if (selectedCargo.isPresent()) {
            Cargo upCargo = cargoRepository.findById(cargo.getId()).orElseThrow(() -> new NotFoundEx("Cargo is not found"));
            if (checkingAuthorization.check(upCargo.getUserEmail())) {
                cargoRepository.saveAndFlush(cargo);
            } else {
                throw new ForbiddenEx("You can't update not your cargo");
            }
        } else {
            throw new NotFoundEx("Cargo is not found");
        }
    }

    @Transactional
    public void deleteCargo(int id) {
        Optional<Cargo> selectedCargo = cargoRepository.findById(id);
        if (selectedCargo.isPresent()) {
            if (checkingAuthorization.check(getUserEmail(id))) {
                cargoRepository.deleteById(id);
            } else {
                throw new ForbiddenEx("You can't delete not your cargo");
            }
        } else {
            throw new NotFoundEx("Cargo is not found");
        }
    }

    private String getUserEmail(int id) {
        return cargoRepository.findById(id).get().getUserEmail();
    }

    public List<CargoResponse> findCargoResponseByUserId(int userId) {
        List<CargoResponse> cargos = cargoRepository.findCargoByUserId(userId).stream()
                .map(cargoToCargoResponseMapper::cargoToResponse)
                .collect(Collectors.toList());
        if (!cargos.isEmpty()) {
            return cargos;
        } else {
            throw new NotFoundEx("There are no information from this user");
        }
    }

    public Cargo getCargoById(int id) {
        Optional<Cargo> selectedCargo = cargoRepository.findById(id);
        if (selectedCargo.isPresent()) {
            return selectedCargo.get();
        } else {
            throw new NotFoundEx("Cargo is not found");
        }
    }

    public List<Cargo> getAllCargo() {
        List<Cargo> cargo = cargoRepository.findAll();
        if (!cargo.isEmpty()) {
            return cargo;
        } else {
            throw new NotFoundEx("There are no information cargo");
        }
    }

    public List<Cargo> findCargoByUserId(int userId) {
        List<Cargo> cargo = cargoRepository.findCargoByUserId(userId);
        if (!cargo.isEmpty()) {
            return cargo;
        } else {
            throw new NotFoundEx("There are no information from this user");
        }
    }

    @Transactional
    public void deleteCargoByAdmin(int id) {
        cargoRepository.findById(id).orElseThrow(() -> new NotFoundEx("Cargo is not found"));
    }

    public void updateCargoAdmin(Cargo cargo) {
        cargoRepository.findById(cargo.getId()).orElseThrow(() -> new NotFoundEx("Cargo is not found"));
        cargoRepository.saveAndFlush(cargo);
    }
}