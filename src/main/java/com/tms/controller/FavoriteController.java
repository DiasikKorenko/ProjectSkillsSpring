package com.tms.controller;

import com.tms.domain.*;
import com.tms.repository.FavoriteRepositoryTransport;
import com.tms.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    FavoriteServiceTransport favoriteServiceTransport;
    FavoriteServiceCargo favoriteServiceCargo;
    UserService userService;
    TransportService transportService;
    CargoService cargoService;

    @Autowired
    public FavoriteController(FavoriteServiceTransport favoriteServiceTransport, FavoriteServiceCargo favoriteServiceCargo, UserService userService, TransportService transportService, CargoService cargoService) {
        this.favoriteServiceTransport = favoriteServiceTransport;
        this.favoriteServiceCargo = favoriteServiceCargo;
        this.userService = userService;
        this.transportService = transportService;
        this.cargoService = cargoService;

    }

    @Operation(summary = "Добавление транспорта в избранное")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер!"), @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @PostMapping("/addTransport")
    public ResponseEntity<?> addTransport(int userId, int transportId) {
        boolean ft = favoriteServiceTransport.addTransport(userId, transportId);
        if (!ft) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("ID НЕ СУЩЕТСВУЕТ:");
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Добавление груза в избранное")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер!"), @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @PostMapping("/addCargo")
    public ResponseEntity<?> addCargo(int userId, int cargoId) {
        boolean ft = favoriteServiceCargo.addCargo(userId, cargoId);
        if (!ft) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("ТАКОЙ ID НЕ СУЩЕТСВУЕТ:");
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление транспорта из избранного по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер,транспорт удален из избранного!"), @ApiResponse(responseCode = "400", description = "Не получилось удалить транспорт..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @DeleteMapping("/transport/{id}")
    public ResponseEntity<HttpStatus> deleteTransport(@PathVariable int id) {
        FavoritesTransport favoritesTransport = favoriteServiceTransport.getTransportById(id);
        if (favoritesTransport == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            favoriteServiceTransport.deleteFavoriteTransport(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Удаление груза из избранного по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер,транспорт удален из избранного!"), @ApiResponse(responseCode = "400", description = "Не получилось удалить транспорт..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @DeleteMapping("/cargo/{id}")
    public ResponseEntity<HttpStatus> deleteCargo(@PathVariable int id) {
        FavoritesCargo favoritesCargo = favoriteServiceCargo.getCargoById(id);
        if (favoritesCargo == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            favoriteServiceCargo.deleteFavoriteCargo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Просмотр всех транспортов в избранном")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер,вот транспорт!"), @ApiResponse(responseCode = "400", description = "Не получилось найти все транспорты..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @GetMapping("/transport")
    public ResponseEntity<ArrayList<FavoritesTransport>> getAllTransport(int userId) {
        ArrayList<FavoritesTransport> list = favoriteServiceTransport.findAllByUserId(userId);
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Просмотр всех грузов в избранном")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер,вот транспорт!"), @ApiResponse(responseCode = "400", description = "Не получилось найти все транспорты..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @GetMapping("/cargo")
    public ResponseEntity<ArrayList<FavoritesCargo>> getAllCargo(int userId) {
        ArrayList<FavoritesCargo> list = favoriteServiceCargo.findAllByUserId(userId);
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Добавление транспорта в избранное,авторизированным пользователем")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер!"), @ApiResponse(responseCode = "400", description = "Не получилось добавить по id..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @PostMapping("/transport/currentUser")
    public ResponseEntity<?> addCurrentUserTransport(int transportId) {
        boolean ft = favoriteServiceTransport.addCurrentTransport(userService.getCurrentUser().getId(), transportId);
        if (!ft) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID НЕ СУЩЕТСВУЕТ:");
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Добавление груза в избранное,авторизированным пользователем")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер!"), @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @PostMapping("/cargo/currentUser")
    public ResponseEntity<?> addCurrentUserCargo(int cargoId) {
        boolean ft = favoriteServiceCargo.addCurrentUserCargo(userService.getCurrentUser().getId(), cargoId);
        if (!ft) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" ID НЕ СУЩЕТСВУЕТ:");
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Просмотр всех транспортов в избранном,авторизированным пользователем")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер,вот транспорт!"), @ApiResponse(responseCode = "400", description = "Не получилось найти все транспорты..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @GetMapping("/transport/currentUser")
    public ResponseEntity<ArrayList<FavoritesTransport>> getCurrentUserAllTransport() {
        ArrayList<FavoritesTransport> list = favoriteServiceTransport.findAllByUserId(userService.getCurrentUser().getId());
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Просмотр всех грузов в избранном,авторизованным пользователем ")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер,вот грузы!"), @ApiResponse(responseCode = "400", description = "Не получилось найти все грузы..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @GetMapping("/cargo/currentUser")
    public ResponseEntity<ArrayList<FavoritesCargo>> getCurrentUserAllCargo() {
        ArrayList<FavoritesCargo> list = favoriteServiceCargo.findAllByUserId(userService.getCurrentUser().getId());
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
