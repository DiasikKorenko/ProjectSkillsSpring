package com.tms.controller;

import com.tms.domain.Cargo;
import com.tms.domain.Subscription;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.service.SubscriptionService;
import com.tms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.info.Contact;
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

import java.sql.Date;
import java.util.Objects;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    SubscriptionService subscriptionService;
    UserService userService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @Operation(summary = "Он вам отдаст подписку по ее id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@Parameter(description = "Эта та самая id которую нужно передать") @PathVariable int id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @Operation(summary = "Он вам создаст подписку по id пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PostMapping
    public ResponseEntity<Subscription> createSubscription(int userId) {
        Subscription subscription = subscriptionService.createSubscription(userId);
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Изменение подписки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,подписка изменена!"),
            @ApiResponse(responseCode = "400", description = "Не получилось изменить транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PutMapping
    public void updateSubscription(@RequestBody Subscription subscription) {
        subscriptionService.updateSubscription(subscription);
    }

    @Operation(summary = "Удаление подписки по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт удален!"),
            @ApiResponse(responseCode = "400", description = "Не получилось удалить транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSubscription(@PathVariable int id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            subscriptionService.deleteSubscription(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Просмотр подписки, которую оформил авторизированный пользователь")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping("/currentUser")
    public ResponseEntity<Subscription> getSubscriptionByCurrentUserId() {
        Subscription subscription = subscriptionService.getSubscriptionByCurrentUserId(userService.getCurrentUser().getId());
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @Operation(summary = "Создание подписки,для авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось создать ..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PostMapping("/currentUser")
    public ResponseEntity<?> createCurrentUserSubscription() {
        Subscription subscription = subscriptionService.createCurrentUserSubscription(userService.getCurrentUser().getId());
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление подписки по id,которую оформил авторизованный пользователь")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,подписка удалена!"),
            @ApiResponse(responseCode = "400", description = "Не получилось удалить подписку..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/currentUser/{id}")
    public ResponseEntity<?> deleteCurrentUserSubscription(@PathVariable int id) {
        boolean subscription = subscriptionService.deleteCurrentUserSubscription(id, userService.getCurrentUser().getId());
        if (!subscription) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}



