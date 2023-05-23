package com.tms.controller;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.domain.response.CargoResponse;
import com.tms.domain.response.TransportResponse;
import com.tms.exception.BadRequestEx;
import com.tms.service.CargoService;
import com.tms.service.UserService;
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

@RestController
@RequestMapping("/cargo")
public class CargoController {
    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }
    @Operation(summary = "Cargo information for the user")
    @GetMapping("/{id}")
    public ResponseEntity<CargoResponse> getCargoResponseById(@PathVariable int id) {
        return new ResponseEntity<>(cargoService.getCargoResponseById(id), HttpStatus.OK);
    }

    @Operation(summary = "All information about cargo for the user")
    @GetMapping
    public ResponseEntity<List<CargoResponse>> getAllCargoResponse() {
        return new ResponseEntity<>(cargoService.getAllCargoResponse(), HttpStatus.OK);
    }

    @Operation(summary = "Creation of cargo")
    @PostMapping()
    public ResponseEntity<Cargo> createTransport(@RequestBody Cargo cargo, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            cargoService.createCargo(cargo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }
    @Operation(summary = "Cargo change for the user")
    @PutMapping
    public ResponseEntity<HttpStatus> updateCargo(@RequestBody @Valid Cargo cargo, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            cargoService.updateCargo(cargo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }

    @Operation(summary = "Deleting a cargo for the user")
    @DeleteMapping
    public ResponseEntity<Cargo> deleteCargo(@RequestParam int id) {
        cargoService.deleteCargo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "All cargo owned by one user")
    @GetMapping("/fromUser/{userId}")
    public ResponseEntity<List<CargoResponse>> findCargoResponseByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(cargoService.findCargoResponseByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Cargo information for the admin (in database)")
    @GetMapping("/admin/{id}")
    public ResponseEntity<Cargo> getCargoById(@PathVariable int id) {
        return new ResponseEntity<>(cargoService.getCargoById(id), HttpStatus.OK);
    }

    @Operation(summary = "All information about cargo for the admin (in database)")
    @GetMapping("/admin")
    public ResponseEntity<List<Cargo>> getAllCargo() {
        return new ResponseEntity<>(cargoService.getAllCargo(), HttpStatus.OK);
    }

    @Operation(summary = "All cargo owned by one user for the admin (in database)")
    @GetMapping("/admin/fromUser/{userId}")
    public ResponseEntity<List<Cargo>> findCargoByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(cargoService.findCargoByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Deleting a cargo for the admin")
    @DeleteMapping("/admin")
    public ResponseEntity<Cargo> deleteCargoByAdmin(@RequestParam int id) {
        cargoService.deleteCargoByAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Cargo change for the admin")
    @PutMapping("/admin")
    public ResponseEntity<HttpStatus> updateCargoAdmin(@RequestBody @Valid Cargo cargo, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            cargoService.updateCargoAdmin(cargo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }
}