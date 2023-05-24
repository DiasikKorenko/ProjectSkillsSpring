package com.tms.repository;

import com.tms.domain.FavoritesTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepositoryTransport extends JpaRepository<FavoritesTransport, Integer> {

    List<FavoritesTransport> findAllByuserId(int userId);
}
