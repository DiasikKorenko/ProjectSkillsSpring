package com.tms.controller;

import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transport")
public class TransportController {

    TransportService transportService;

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
  /*  @GetMapping("/{id}")
    public Transport getTransportById(@PathVariable int id) {
        return transportService.getTransportById(id);
    }*/






    @PostMapping()
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
       /* boolean result = transportService.deleteTransport(Integer.parseInt(id),Integer.parseInt(userId));*/
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

    }
}
