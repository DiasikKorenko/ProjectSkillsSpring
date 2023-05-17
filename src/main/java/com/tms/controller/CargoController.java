package com.tms.controller;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.service.CargoService;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Component
@RequestMapping("/cargo")
public class CargoController {

    CargoService cargoService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Operation(summary = "Он вам отдаст груз по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getCagoById(@Parameter(description = "Эта та самая id которую нужно передать")@PathVariable int id) {
        Cargo cargo = cargoService.getCargoById(id);
        if (cargo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cargo,HttpStatus.OK);
    }


    @Operation(summary = "Создание груза")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,груз создан!"),
            @ApiResponse(responseCode = "400", description = "Не получилось создать груз..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PostMapping
    public ResponseEntity<?> createCargo(@RequestBody @Valid Cargo cargo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("ВАЛИДНОСТЬ ДАННЫХ:  " + o);
            }
        }
        cargoService.createCargo(cargo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Изменение груза")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,груз изменен!"),
            @ApiResponse(responseCode = "400", description = "Не получилось изменить груз..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PutMapping
    public ResponseEntity<?> updateTransport(@RequestBody @Valid Cargo cargo,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("ВАЛИДНОСТЬ ДАННЫХ:  " + o);
            }
        }
        cargoService.updateCargo(cargo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удаление груза")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,груз удален!"),
            @ApiResponse(responseCode = "400", description = "Не получилось удалить груз..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCargo(@PathVariable int id) {
        Cargo cargo = cargoService.getCargoById(id);
        if (cargo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            cargoService.deleteCargo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Просмотр всех грузов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,вот грузы!"),
            @ApiResponse(responseCode = "400", description = "Не получилось найти все грузы..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping
    public ResponseEntity<ArrayList<Cargo>> getAllCargo() {
        ArrayList<Cargo> list = cargoService.getAllCargo();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }





}
















