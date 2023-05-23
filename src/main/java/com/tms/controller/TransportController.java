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
import org.springframework.web.bind.annotation.*;

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























/*
    @Operation(summary = "Он вам отдаст транспорт по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Transport> getTransportById(@Parameter(description = "Эта та самая id которую нужно передать") @PathVariable int id) {
        Transport transport = transportService.getTransportById(id);
        if (transport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transport, HttpStatus.OK);

    }


    @Operation(summary = "Создание транспорта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт создан!"),
            @ApiResponse(responseCode = "400", description = "Не получилось создать транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PostMapping
    public ResponseEntity<?> createTransport(@RequestBody @Valid Transport transport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("ВАЛИДНОСТЬ ДАННЫХ:  " + o);
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
    public ResponseEntity<?> updateTransport(@RequestBody @Valid Transport transport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("ВАЛИДНОСТЬ ДАННЫХ:  " + o);
            }
        }
        transportService.updateTransport(transport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удаление транспорта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт удален!"),
            @ApiResponse(responseCode = "400", description = "Не получилось удалить транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransport(@PathVariable int id) {
        Transport transport = transportService.getTransportById(id);
        if (transport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            transportService.deleteTransport(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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
*/

   /* @Operation(summary = "Просмотр транспортов которые запостил пользователь ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать транспорты..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping("/currentUser")
    public ResponseEntity<?> getTransportCurrentUser() {
        List<Transport> transports = transportService.getCurrentUserTransports(userService.getCurrentUser().getId());
        if (transports == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transports, HttpStatus.OK);
    }*/

   /* @Operation(summary = "Удаление транспорта по id,который запостил авторизованный пользователь")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт удален!"),
            @ApiResponse(responseCode = "400", description = "Не получилось удалить транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/currentUser/{id}")
    public ResponseEntity<?> deleteTransportCurrentUser(@PathVariable int id) {
        boolean transport = transportService.deleteCurrentUserTransport(id, userService.getCurrentUser().getId());
        if (!transport) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }*/

   /* @Operation(summary = "Создание транспорта,авторизированным пользователем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт создан!"),
            @ApiResponse(responseCode = "400", description = "Не получилось создать транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PostMapping("/currentUser")
    public ResponseEntity<?> currentUserCreateTransport(@RequestBody @Valid Transport transport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("ВАЛИДНОСТЬ ДАННЫХ:  " + o);
            }
        }
        transportService.currentUserCreateTransport(userService.getCurrentUser().getId(), transport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/


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

