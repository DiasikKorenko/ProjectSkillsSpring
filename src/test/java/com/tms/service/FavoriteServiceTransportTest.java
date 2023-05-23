package com.tms.service;

import com.tms.domain.FavoritesTransport;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.repository.FavoriteRepositoryTransport;
import com.tms.repository.TransportRepository;
import com.tms.repository.UserRepository;
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
class FavoriteServiceTransportTest {
    @InjectMocks
    private FavoriteServiceTransport  favoriteServiceTransport;
    @Mock
    private FavoriteRepositoryTransport favoriteRepositoryTransport;
    @Mock
    private CheckingAuthorization checkingAuthorization;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TransportRepository transportRepository;
    private int id;
    @Mock
    private Transport transport;
    @Mock
    private User user;
    @Mock
    private FavoritesTransport favoritesTransport;

    private final List<FavoritesTransport> favoritesTransports = new ArrayList<>();

    @BeforeEach
    public void init() {
        id = 1;
        favoritesTransport = new FavoritesTransport();
        favoritesTransport.setId(1);
        favoritesTransport.setTransportId(1);
        favoritesTransport.setUserId(1);
        favoritesTransports.add(favoritesTransport);
       }


    @Test
    public void createTransportFavouriteTest() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(transportRepository.findById(id)).thenReturn(Optional.of(transport));
        favoriteServiceTransport.createTransportFavourite(favoritesTransport);
        verify(favoriteRepositoryTransport).save(favoritesTransport);
    }

    @Test
    public void removeServiceFromUserTest() {
        when(favoriteRepositoryTransport.findById(id)).thenReturn(Optional.of(favoritesTransport));
        when(checkingAuthorization.check(user.getEmail())).thenReturn(true);
        favoriteServiceTransport.removeTransportFromUser(id);
        verify(favoriteRepositoryTransport).deleteById(id);
    }
}