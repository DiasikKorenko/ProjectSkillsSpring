package com.tms.service;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import com.tms.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class FavoriteService {

    FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public boolean add(int userId, int transportId, int cargoId) {
        return favoriteRepository.add(userId, transportId, cargoId);
    }

    public ArrayList<Cargo> getCargo(int userId) {
        return favoriteRepository.getCargo(userId);
    }

    public ArrayList<Transport> getTransport(int id) {
        return favoriteRepository.getTransport(id);
    }

///gffteudryry
}
