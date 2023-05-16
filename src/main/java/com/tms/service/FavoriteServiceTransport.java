package com.tms.service;

import com.tms.domain.FavoritesTransport;
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
    public FavoriteServiceTransport(FavoriteRepositoryTransport favoriteRepositoryTransport, CargoRepository cargoRepository, TransportRepository transportRepository,UserRepository userRepository) {
        this.favoriteRepositoryTransport = favoriteRepositoryTransport;
        this.transportRepository = transportRepository;
        this.userRepository = userRepository;
    }
    public FavoritesTransport addTransport(int userId, int transportId) {
        FavoritesTransport btf = new FavoritesTransport();
            btf.setUserId(userId);
            btf.setTransportId(transportId);
            return favoriteRepositoryTransport.saveAndFlush(btf);

    }

    @Transactional
    public void deleteById(int id) {
        favoriteRepositoryTransport.deleteById(id);
    }

    public List<FavoritesTransport> findAllByUserId(int id) {
        return favoriteRepositoryTransport.findByUserId(id);
    }
}

