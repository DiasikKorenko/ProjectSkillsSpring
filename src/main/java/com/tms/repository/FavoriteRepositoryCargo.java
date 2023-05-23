package com.tms.repository;

import com.tms.domain.FavoritesCargo;
import com.tms.domain.FavoritesTransport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteRepositoryCargo extends JpaRepository<FavoritesCargo,Integer> {
    List<FavoritesCargo> findAllByuserId(int userId);
}
