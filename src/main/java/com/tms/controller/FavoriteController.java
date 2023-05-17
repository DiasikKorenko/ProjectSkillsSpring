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

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    FavoriteServiceTransport favoriteServiceTransport;
    @Autowired
    FavoriteServiceCargo favoriteServiceCargo;
    @Autowired
    UserService userService;
    @Autowired
    TransportService transportService;
    @Autowired
    CargoService cargoService;

    @Autowired
    public FavoriteController(FavoriteServiceTransport favoriteServiceTransport, FavoriteServiceCargo favoriteServiceCargo) {
        this.favoriteServiceTransport = favoriteServiceTransport;
        this.favoriteServiceCargo = favoriteServiceCargo;

    }

    @Operation(summary = "Добавление транспорта в избранное")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер!"), @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @PostMapping("/addTransport")
    public ResponseEntity<HttpStatus> addTransport(int userId, int transportId) {
        FavoritesTransport ft = favoriteServiceTransport.addTransport(userId, transportId);
        if (ft == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            favoriteServiceTransport.deleteById(id);
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

    @Operation(summary = "Добавление груза в избранное")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер!"), @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @PostMapping("/addCargo")
    public ResponseEntity<HttpStatus> addCargo(int userId, int cargoId) {
        FavoritesCargo ft = favoriteServiceCargo.addCargo(userId, cargoId);
        if (ft == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление груза из избранного по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер,транспорт удален из избранного!"), @ApiResponse(responseCode = "400", description = "Не получилось удалить транспорт..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @DeleteMapping("/cargo/{id}")
    public ResponseEntity<HttpStatus> deleteCargoById(@PathVariable int id) {
        FavoritesCargo favoritesCargo = favoriteServiceCargo.getCargoById(id);
        if (favoritesCargo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            favoriteServiceCargo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Просмотр всех транспортов в избранном")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Все супер,вот транспорт!"), @ApiResponse(responseCode = "400", description = "Не получилось найти все транспорты..."), @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")})
    @GetMapping("/cargo")
    public ResponseEntity<ArrayList<FavoritesCargo>> getAllCargo(int userId) {
        ArrayList<FavoritesCargo> list = favoriteServiceCargo.findAllByUserId(userId);
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
