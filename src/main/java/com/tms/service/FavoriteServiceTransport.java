package com.tms.service;

import com.tms.domain.FavoritesTransport;
import com.tms.domain.Reviews;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.exception.ForbiddenEx;
import com.tms.exception.NotFoundEx;
import com.tms.repository.CargoRepository;
import com.tms.repository.FavoriteRepositoryTransport;
import com.tms.repository.TransportRepository;
import com.tms.repository.UserRepository;
import com.tms.security.CheckingAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceTransport {

    private final FavoriteRepositoryTransport favoriteRepositoryTransport;
    private final TransportRepository transportRepository;
    private final UserRepository userRepository;
    private final CheckingAuthorization checkingAuthorization;

    @Autowired
    public FavoriteServiceTransport(FavoriteRepositoryTransport favoriteRepositoryTransport, TransportRepository transportRepository, UserRepository userRepository, CheckingAuthorization checkingAuthorization) {
        this.favoriteRepositoryTransport = favoriteRepositoryTransport;
        this.transportRepository = transportRepository;
        this.userRepository = userRepository;
        this.checkingAuthorization = checkingAuthorization;
    }

    public void createTransportFavourite(FavoritesTransport favoritesTransport) {
        Optional<User> selectedFavoritesUser = userRepository.findById(favoritesTransport.getUserId());
        Optional<Transport> selectedFavoritesTransport = transportRepository.findById(favoritesTransport.getTransportId());
        if (selectedFavoritesTransport.isPresent() && selectedFavoritesUser.isPresent()) {
            favoritesTransport.setUserEmail(selectedFavoritesUser.get().getEmail());
            favoriteRepositoryTransport.save(favoritesTransport);
        } else {
            throw new NotFoundEx("Not found id user/transport");
        }
    }

    @Transactional
    public void removeTransportFromUser(int id) {
        Optional<FavoritesTransport> selectedFavTransport = favoriteRepositoryTransport.findById(id);
        if (selectedFavTransport.isPresent()) {
            if (checkingAuthorization.check(getUserEmail(id))) {
                favoriteRepositoryTransport.deleteById(id);
            } else {
                throw new ForbiddenEx("You can't delete not your FavoriteTransport");
            }
        } else {
            throw new NotFoundEx("FavoriteTransport is not found");
        }
    }

    private String getUserEmail(int id) {
        return favoriteRepositoryTransport.findById(id).get().getUserEmail();
    }

    public List<FavoritesTransport> getAllFavoritesTransport(int userId) {
        List<FavoritesTransport> ft = favoriteRepositoryTransport.findAllByuserId(userId);
        if (!ft.isEmpty()) {
            return ft;
        } else {
            throw new NotFoundEx("There are no any favorite for this user");
        }
    }
}
