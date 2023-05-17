package com.tms.controller;


import com.tms.domain.User;
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
import org.springframework.validation.BindingResult;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Он вам отдаст юзера по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@Parameter(description = "Эта та самая id которую нужно передать") @PathVariable int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Создание пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (userService.loginExist(user.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("ДАННАЯ ПОЧТА УЖЕ СУЩЕТСВУЕТ:");
        } else if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("ВАЛИДНОСТЬ ДАННЫХ:  " + o);
            }
        }
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Изменение пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("ВАЛИДНОСТЬ ДАННЫХ:  " + o);
            }
        }
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удаление пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Просмотр всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping
    public ResponseEntity<ArrayList<User>> getAllUser() {
        ArrayList<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Просмотр учетной записи авторизованным пользователем ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id авториз пользователя..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping("/currentUser")
    public ResponseEntity<User> getCurrentUser() {
        User user = userService.getCurrentUser();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @Operation(summary = "Изменение авторизованного пользователя пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PutMapping("/currentUser")
    public ResponseEntity<?> updateCurrentUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn("We have bindingResult error : " + o);
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("ВАЛИДНОСТЬ ДАННЫХ:  " + o);
            }
        }
        userService.updateCurrentUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удаление авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось удалить по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/currentUser")
    public ResponseEntity<HttpStatus> deleteCurrentUser() {

       userService.deleteCurrentUser(userService.getCurrentUserId());
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
