package com.tms.service;

import com.tms.domain.Cargo;
import com.tms.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CargoService {

    CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo getCargoById(int id) {
        return cargoRepository.getCargoById(id);
    }

    public boolean createCargo(int userId, int weightCargo, int widthCargo, int lenghtCargo, int hight, String states, String route) {
        return cargoRepository.createCargo(userId,weightCargo,widthCargo,lenghtCargo,hight,states,route);
    }

    public boolean updateCargo(int weightCargo, int widthCargo, int lenghtCargo, int hight, String states, String route, int id, int userId) {
        return cargoRepository.updateCargo(weightCargo,widthCargo,lenghtCargo,hight,states,route,id,userId);
    }

    public boolean deleteCargo(int id, int userId) {
        return cargoRepository.deleteCargo(id,userId);
    }

    public List<Cargo> getUserCargoById(int userId) {
        return cargoRepository.getUserCargoById(userId);
    }

    public List<Cargo> getAllCargos() {
        return cargoRepository.getAllCargos();
    }

}
