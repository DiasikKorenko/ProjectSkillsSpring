package com.tms.service;


import com.tms.domain.*;
import com.tms.exception.ForbiddenEx;
import com.tms.exception.NotFoundEx;
import com.tms.repository.CargoRepository;
import com.tms.repository.FavoriteRepositoryCargo;
import com.tms.repository.UserRepository;
import com.tms.security.CheckingAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceCargo {

    private final FavoriteRepositoryCargo favoriteRepositoryCargo;
    private final CargoRepository cargoRepository;
    private final UserRepository userRepository;
    private final CheckingAuthorization checkingAuthorization;

    private final UserService userService;

    @Autowired
    public FavoriteServiceCargo(FavoriteRepositoryCargo favoriteRepositoryCargo, CargoRepository cargoRepository, UserRepository userRepository, CheckingAuthorization checkingAuthorization, UserService userService) {
        this.favoriteRepositoryCargo = favoriteRepositoryCargo;
        this.cargoRepository = cargoRepository;
        this.userRepository = userRepository;
        this.checkingAuthorization = checkingAuthorization;
        this.userService = userService;
    }

    public void createCargoFavourite(FavoritesCargo favoritesCargo) {
        Optional<User> selectedFavoritesUser = userRepository.findById(favoritesCargo.getUserId());
        Optional<Cargo> selectedFavoritesCargo = cargoRepository.findById(favoritesCargo.getCargoId());
        if (selectedFavoritesCargo.isPresent() && selectedFavoritesUser.isPresent()) {
            if (checkingAuthorization.check(selectedFavoritesUser.get().getEmail())) {
                favoritesCargo.setUserEmail(selectedFavoritesUser.get().getEmail());
                favoriteRepositoryCargo.save(favoritesCargo);
            }else{
                throw new ForbiddenEx("You can't add FavoriteCargo another user");
            }
        } else {
            throw new NotFoundEx("Not found id user/cargo");

        }
    }

    @Transactional
    public void removeCargoFromUser(int id) {
        Optional<FavoritesCargo> selectedFavTransport = favoriteRepositoryCargo.findById(id);
        if (selectedFavTransport.isPresent()) {
            if (checkingAuthorization.check(getUserEmail(id))) {
                favoriteRepositoryCargo.deleteById(id);
            } else {
                throw new ForbiddenEx("You can't delete not your FavoriteCargo");
            }
        } else {
            throw new NotFoundEx("FavoriteCargo is not found");
        }
    }

    private String getUserEmail(int id) {
        return favoriteRepositoryCargo.findById(id).get().getUserEmail();
    }

    public List<FavoritesCargo> getAllFavoritesCargo(int userId) {
        List<FavoritesCargo> ft = favoriteRepositoryCargo.findAllByuserId(userId);
        if (!ft.isEmpty()) {
            return ft;
        } else {
            throw new NotFoundEx("There are no any favorite for this user");
        }
    }
}
