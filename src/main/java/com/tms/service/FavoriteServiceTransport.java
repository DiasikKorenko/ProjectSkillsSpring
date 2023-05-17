package com.tms.service;

import com.tms.domain.Cargo;
import com.tms.domain.FavoritesTransport;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.repository.CargoRepository;
import com.tms.repository.FavoriteRepositoryTransport;
import com.tms.repository.TransportRepository;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceTransport {

    FavoriteRepositoryTransport favoriteRepositoryTransport;

    TransportRepository transportRepository;
    UserRepository userRepository;

    @Autowired
    public FavoriteServiceTransport(FavoriteRepositoryTransport favoriteRepositoryTransport, CargoRepository cargoRepository, TransportRepository transportRepository, UserRepository userRepository) {
        this.favoriteRepositoryTransport = favoriteRepositoryTransport;
        this.transportRepository = transportRepository;
        this.userRepository = userRepository;
    }

    public boolean addTransport(int userId, int transportId) {
        Transport ftDBEntity = transportRepository.findById(transportId).orElse(null);
        User userDBEntity = userRepository.findById(userId).orElse(null);
        if (ftDBEntity == null || userDBEntity == null)
            return false;
        FavoritesTransport btf = new FavoritesTransport();
        btf.setUserId(userId);
        btf.setTransportId(transportId);
        favoriteRepositoryTransport.saveAndFlush(btf);
        return true;
    }

/*    @Transactional
    public boolean deleteById(int id) {
        FavoritesTransport ftDBEntity = favoriteRepositoryTransport.findById(id).orElse(null);
        if (ftDBEntity == null)
            return false;
        favoriteRepositoryTransport.deleteById(id);
        return true;
    }*/

    @Transactional
    public void deleteFavoriteTransport(int id) {
        favoriteRepositoryTransport.deleteById(id);
    }

    public ArrayList<FavoritesTransport> findAllByUserId(int id) {
        return favoriteRepositoryTransport.findByUserId(id);
    }

    public FavoritesTransport getTransportById(int id) {
        return favoriteRepositoryTransport.findById(id).orElse(null);
    }

    public boolean addCurrentTransport(int userId, int transportId) {
        Transport ftDBEntity = transportRepository.findById(transportId).orElse(null);
        if (ftDBEntity == null)
            return false;
        FavoritesTransport btf = new FavoritesTransport();
        btf.setUserId(userId);
        btf.setTransportId(transportId);
        favoriteRepositoryTransport.saveAndFlush(btf);
        return true;

    }

}

