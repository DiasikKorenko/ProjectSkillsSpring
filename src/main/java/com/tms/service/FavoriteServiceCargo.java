package com.tms.service;


import com.tms.domain.FavoritesCargo;
import com.tms.repository.CargoRepository;
import com.tms.repository.FavoriteRepositoryCargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceCargo {

    private final FavoriteRepositoryCargo favoriteRepositoryCargo;
    private  final CargoRepository cargoRepository;


    @Autowired
    public FavoriteServiceCargo(FavoriteRepositoryCargo favoriteRepositoryCargo, CargoRepository cargoRepository) {
        this.favoriteRepositoryCargo = favoriteRepositoryCargo;
        this.cargoRepository = cargoRepository;
    }


    public void addCargo(int userId, int cargoId) {
        FavoritesCargo btf = new FavoritesCargo();
        btf.setUserId(userId);
        btf.setCargoId(cargoId);
        favoriteRepositoryCargo.saveAndFlush(btf);
    }

    public List<FavoritesCargo> findAllByUserId(int id) {
        return favoriteRepositoryCargo.findByUserId(id);
    }

    public void deleteById(int id) {
        favoriteRepositoryCargo.deleteById(id);
    }
}
