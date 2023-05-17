package com.tms.service;


import com.tms.domain.Cargo;
import com.tms.domain.FavoritesCargo;
import com.tms.repository.CargoRepository;
import com.tms.repository.FavoriteRepositoryCargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceCargo {

    FavoriteRepositoryCargo favoriteRepositoryCargo;
    CargoRepository cargoRepository;


    @Autowired
    public FavoriteServiceCargo(FavoriteRepositoryCargo favoriteRepositoryCargo, CargoRepository cargoRepository) {
        this.favoriteRepositoryCargo = favoriteRepositoryCargo;
        this.cargoRepository = cargoRepository;
    }


    public FavoritesCargo addCargo(int userId, int cargoId) {
        FavoritesCargo btf = new FavoritesCargo();
        btf.setUserId(userId);
        btf.setCargoId(cargoId);
        return favoriteRepositoryCargo.saveAndFlush(btf);
    }

    public ArrayList<FavoritesCargo> findAllByUserId(int id) {
        return favoriteRepositoryCargo.findByUserId(id);
    }

    public void deleteById(int id) {
        favoriteRepositoryCargo.deleteById(id);
    }

    public FavoritesCargo getCargoById(int id) {
        return favoriteRepositoryCargo.findById(id).orElse(null);
    }
}
