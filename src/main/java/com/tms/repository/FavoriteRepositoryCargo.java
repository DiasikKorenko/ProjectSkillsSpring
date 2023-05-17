package com.tms.repository;

import com.tms.domain.FavoritesCargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface FavoriteRepositoryCargo extends JpaRepository<FavoritesCargo,Integer> {
    ArrayList<FavoritesCargo> findByUserId(int userId);
}
