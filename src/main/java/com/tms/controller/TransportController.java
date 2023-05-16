package com.tms.controller;

import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transport")
public class TransportController {

    TransportService transportService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @Operation(summary = "Он вам отдаст транспорт по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
   @GetMapping("/{id}")
    public Transport getTransportById(@Parameter(description = "Эта та самая id которую нужно передать")@PathVariable int id) {
        return transportService.getTransportById(id);

    }


    @Operation(summary = "Создание транспорта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт создан!"),
            @ApiResponse(responseCode = "400", description = "Не получилось создать транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PostMapping
    public ResponseEntity<HttpStatus> createTransport(@RequestBody Transport transport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
            }
        }
        transportService.createTransport(transport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Изменение транспорта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт изменен!"),
            @ApiResponse(responseCode = "400", description = "Не получилось изменить транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PutMapping
    public void updateTransport(@RequestBody Transport transport) {
        transportService.updateTransport(transport);
    }

    @Operation(summary = "Удаление транспорта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт удален!"),
            @ApiResponse(responseCode = "400", description = "Не получилось удалить транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransport(@PathVariable int id) {
        transportService.deleteTransport(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Просмотр всех транспортов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,вот транспорт!"),
            @ApiResponse(responseCode = "400", description = "Не получилось найти все транспорты..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping
    public ResponseEntity<ArrayList<Transport>> getAllTransport() {
        ArrayList<Transport> list = transportService.getAllTransport();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }



 /*   @PostMapping()
    public void createTransport(@RequestBody Transport transport) {
        transportService.createTransport(transport);

    }

    @PutMapping()
    public String createTransport(
            @RequestParam int id,
            @RequestParam String typeTransport,
            @RequestParam int weightTransport,
            @RequestParam int volumeTransport,
            @RequestParam int userId) {
        boolean result = transportService.updateTransport(typeTransport,weightTransport,volumeTransport,id,userId);
        if (result) {
            return "successfully";
        }
        return "unsuccessfully";

    }

    @DeleteMapping("/{id}{userId}")
    public String deletedTransport(@PathVariable int id,
                                   @PathVariable int userId) {
        boolean result = transportService.deleteTransport(Integer.parseInt(id),Integer.parseInt(userId));
        if (transportService.deleteTransport(id,userId)) {
            return "successfully";
        }
        return "unsuccessfully";
    }

    @GetMapping("/giveTransport/{userId}")
    public String getUserTransportById(@PathVariable int userId, Model model) {

        List<Transport> list = transportService.getUserTransportById(userId);
        model.addAttribute("transport", list);
        return "UserTransport";
    }

    @GetMapping("/giveAllTransport")
    public ResponseEntity<ArrayList< Transport >> getALLTransport() {
        ArrayList<Transport> list = (ArrayList<Transport>) transportService.getAllTransports();
        return new ResponseEntity<>(list,(!list.isEmpty())?HttpStatus.OK: HttpStatus.NOT_FOUND);

    }*/
}
