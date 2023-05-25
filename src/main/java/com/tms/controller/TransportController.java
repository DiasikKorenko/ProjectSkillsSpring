package com.tms.controller;

import com.tms.domain.Transport;
import com.tms.domain.response.TransportResponse;
import com.tms.exception.BadRequestEx;
import com.tms.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transport")
public class TransportController {

    private final TransportService transportService;

    @Autowired
    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @Operation(summary = "Transport information for the user")
    @GetMapping("/{id}")
    public ResponseEntity<TransportResponse> getTransportResponseById(@PathVariable int id) {
        return new ResponseEntity<>(transportService.getTransportResponseById(id), HttpStatus.OK);
    }

    @Operation(summary = "All information about transport for the user")
    @GetMapping
    public ResponseEntity<List<TransportResponse>> getAllTransportResponse() {
        return new ResponseEntity<>(transportService.getAllTransportResponse(), HttpStatus.OK);
    }

    @Operation(summary = "Creation of transport")
    @PostMapping()
    public ResponseEntity<Transport> createTransport(@RequestBody @Valid Transport transport, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            transportService.createTransport(transport);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }

    @Operation(summary = "Transport change for the user")
    @PutMapping
    public ResponseEntity<HttpStatus> updateTransport(@RequestBody @Valid Transport transport, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            transportService.updateTransport(transport);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }

    @Operation(summary = "Deleting a transport for the user")
    @DeleteMapping
    public ResponseEntity<Transport> deleteService(@RequestParam int id) {
        transportService.deleteTransport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "All transport owned by one user")
    @GetMapping("/fromUser/{userId}")
    public ResponseEntity<List<TransportResponse>> findTransportResponseByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(transportService.findTransportResponseByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Transport information for the admin (in database)")
    @GetMapping("/admin/{id}")
    public ResponseEntity<Transport> getTransportById(@PathVariable int id) {
        return new ResponseEntity<>(transportService.getTransportById(id), HttpStatus.OK);
    }

    @Operation(summary = "All information about transport for the admin (in database)")
    @GetMapping("/admin")
    public ResponseEntity<List<Transport>> getAllTransport() {
        return new ResponseEntity<>(transportService.getAllTransport(), HttpStatus.OK);
    }

    @Operation(summary = "All transport owned by one user for the admin (in database)")
    @GetMapping("/admin/fromUser/{userId}")
    public ResponseEntity<List<Transport>> findTransportByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(transportService.findTransportByUserId(userId), HttpStatus.OK);
    }

    @Operation(summary = "Deleting a transport for the admin")
    @DeleteMapping("/admin")
    public ResponseEntity<Transport> deleteTransportByAdmin(@RequestParam int id) {
        transportService.deleteTransportByAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Transport change for the admin")
    @PutMapping("/admin")
    public ResponseEntity<HttpStatus> updateTransportAdmin(@RequestBody @Valid Transport transport, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            transportService.updateTransportAdmin(transport);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new BadRequestEx("Validation error.Check the entered data");
        }
    }
}