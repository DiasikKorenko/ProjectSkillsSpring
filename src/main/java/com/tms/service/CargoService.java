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
  /*  public boolean createCargo(int userId, int weightCargo, int widthCargo, int lenghtCargo, int hight, String states, String route) {
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
*/
}
