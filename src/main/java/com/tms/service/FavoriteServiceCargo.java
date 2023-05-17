package com.tms.service;


import com.tms.domain.*;
import com.tms.repository.CargoRepository;
import com.tms.repository.FavoriteRepositoryCargo;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceCargo {

    FavoriteRepositoryCargo favoriteRepositoryCargo;
    CargoRepository cargoRepository;
    UserRepository userRepository;


    @Autowired
    public FavoriteServiceCargo(FavoriteRepositoryCargo favoriteRepositoryCargo, CargoRepository cargoRepository, UserRepository userRepository) {
        this.favoriteRepositoryCargo = favoriteRepositoryCargo;
        this.cargoRepository = cargoRepository;
        this.userRepository = userRepository;
    }


    public boolean addCargo(int userId, int cargoId) {
        Cargo ftDBEntity = cargoRepository.findById(cargoId).orElse(null);
        User userDBEntity = userRepository.findById(userId).orElse(null);
        if (ftDBEntity == null || userDBEntity == null)
            return false;
        FavoritesCargo btf = new FavoritesCargo();
        btf.setUserId(userId);
        btf.setCargoId(cargoId);
        favoriteRepositoryCargo.saveAndFlush(btf);
        return true;
    }

    public ArrayList<FavoritesCargo> findAllByUserId(int id) {
        return favoriteRepositoryCargo.findByUserId(id);
    }
/*    @Transactional
    public boolean deleteById(int id) {
        FavoritesCargo ftDBEntity = favoriteRepositoryCargo.findById(id).orElse(null);
        if (ftDBEntity == null)
            return false;
        favoriteRepositoryCargo.deleteById(id);
        return true;
    }*/

    @Transactional
    public void deleteFavoriteCargo(int id) {
        favoriteRepositoryCargo.deleteById(id);
    }

    public FavoritesCargo getCargoById(int id) {
        return favoriteRepositoryCargo.findById(id).orElse(null);
    }

    public boolean addCurrentUserCargo(int userId, int cargoId) {
        Cargo ftDBEntity = cargoRepository.findById(cargoId).orElse(null);
        if (ftDBEntity == null)
            return false;
        FavoritesCargo btf = new FavoritesCargo();
        btf.setUserId(userId);
        btf.setCargoId(cargoId);
        favoriteRepositoryCargo.saveAndFlush(btf);
        return true;
    }
}
