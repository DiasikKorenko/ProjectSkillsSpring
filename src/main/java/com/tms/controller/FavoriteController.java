
package com.tms.controller;

import com.tms.domain.*;
import com.tms.service.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteServiceTransport favoriteServiceTransport;
    private final FavoriteServiceCargo favoriteServiceCargo;

    @Autowired
    public FavoriteController(FavoriteServiceTransport favoriteServiceTransport, FavoriteServiceCargo favoriteServiceCargo) {
        this.favoriteServiceTransport = favoriteServiceTransport;
        this.favoriteServiceCargo = favoriteServiceCargo;
    }

    @Operation(summary = "Add transport to user's Favourite")
    @PostMapping("/transport/addFav")
    public ResponseEntity<HttpStatus> addSTransportToUser(@RequestBody FavoritesTransport favoritesTransport) {
        favoriteServiceTransport.createTransportFavourite(favoritesTransport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Remove transport from user's Favourite")
    @DeleteMapping("/transport/removeFav")
    public ResponseEntity<HttpStatus> removeTransportFromUser(@RequestParam int id) {
        favoriteServiceTransport.removeTransportFromUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Add cargo to user's Favourite")
    @PostMapping("/cargo/addFav")
    public ResponseEntity<HttpStatus> addSCargoToUser(@RequestBody FavoritesCargo favoritesCargo) {
        favoriteServiceCargo.createCargoFavourite(favoritesCargo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Remove cargo from user's Favourite")
    @DeleteMapping("/cargo/removeFav")
    public ResponseEntity<HttpStatus> removeCargoFromUser(@RequestParam int id) {
        favoriteServiceCargo.removeCargoFromUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Provide all all transport's information  for one user")
    @GetMapping("/transport/admin/{userId}")
    public ResponseEntity<List<FavoritesTransport>> getAllFavoritesTransport(@PathVariable int userId) {
        return new ResponseEntity<>(favoriteServiceTransport.getAllFavoritesTransport(userId), HttpStatus.OK);
    }

    @Operation(summary = "Provide all all cargo' information  for one user")
    @GetMapping("/cargo/admin/{userId}")
    public ResponseEntity<List<FavoritesCargo>> getAllFavoritesCargo(@PathVariable int userId) {
        return new ResponseEntity<>(favoriteServiceCargo.getAllFavoritesCargo(userId), HttpStatus.OK);
    }
}
