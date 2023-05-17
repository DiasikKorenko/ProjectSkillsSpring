package com.tms.service;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import com.tms.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CargoService {

    CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo getCargoById(int id) {
        return cargoRepository.findById(id).orElse(null);
    }

    public Cargo createCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public Cargo updateCargo(Cargo cargo) {
        return cargoRepository.saveAndFlush(cargo);
    }

    @Transactional
    public void deleteCargo(int id) {
        cargoRepository.deleteById(id);
    }


    public ArrayList<Cargo> getAllCargo() {
        return (ArrayList<Cargo>) cargoRepository.findAll();
    }


    public List<Cargo> getCargosByCurrentUserId(Integer userId) {
        return cargoRepository.findAllByUserId(userId);
    }

    public void CurrentUserCreateCargo(Integer userId, Cargo cargo) {
        cargo.setUserId(userId);
        cargoRepository.saveAndFlush(cargo);
    }

    @Transactional
    public boolean deleteCurrentUserCargo(Integer cargoId, Integer userId) {
        Cargo cargoDBEntity = cargoRepository.findById(cargoId).orElse(null);
        if (cargoDBEntity == null || cargoDBEntity.getUserId() != userId)
            return false;
        cargoRepository.deleteById(cargoId);
        cargoRepository.flush();
        return true;
    }
}
