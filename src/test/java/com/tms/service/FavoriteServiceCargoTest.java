package com.tms.service;

import com.tms.domain.*;
import com.tms.repository.*;
import com.tms.security.CheckingAuthorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceCargoTest {

    @InjectMocks
    private FavoriteServiceCargo  favoriteServiceCargo;
    @Mock
    private FavoriteRepositoryCargo favoriteRepositoryCargo;
    @Mock
    private CheckingAuthorization checkingAuthorization;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CargoRepository cargoRepository;

    private int id;
    @Mock
    private Cargo cargo;
    @Mock
    private User user;
    @Mock
    private FavoritesCargo favoritesCargo;

    private final List<FavoritesCargo> favoritesCargos = new ArrayList<>();

    @BeforeEach
    public void init() {
        id = 1;
        favoritesCargo = new FavoritesCargo();
        favoritesCargo.setId(1);
        favoritesCargo.setCargoId(1);
        favoritesCargo.setUserId(1);
        favoritesCargos.add(favoritesCargo);
    }

    @Test
    public void createCargoFavouriteTest() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(cargoRepository.findById(id)).thenReturn(Optional.of(cargo));
        favoriteServiceCargo.createCargoFavourite(favoritesCargo);
        verify(favoriteRepositoryCargo).save(favoritesCargo);
    }

    @Test
    public void removeCargoFromUserTest() {
        when(favoriteRepositoryCargo.findById(id)).thenReturn(Optional.of(favoritesCargo));
        when(checkingAuthorization.check(user.getEmail())).thenReturn(true);
        favoriteServiceCargo.removeCargoFromUser(id);
        verify(favoriteRepositoryCargo).deleteById(id);
    }
}