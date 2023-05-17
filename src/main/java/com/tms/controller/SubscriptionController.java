package com.tms.controller;

import com.tms.domain.Cargo;
import com.tms.domain.Subscription;
import com.tms.domain.Transport;
import com.tms.domain.User;
import com.tms.service.SubscriptionService;
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

import java.sql.Date;
import java.util.Objects;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    SubscriptionService subscriptionService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
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

    @Operation(summary = "Он вам создаст подписку по вашему id")
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

    @Operation(summary = "Удаление подписки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт удален!"),
            @ApiResponse(responseCode = "400", description = "Не получилось удалить транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransport(@PathVariable int id) {
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            subscriptionService.deleteSubscription(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}

/*






    @PutMapping()
    public String updateCargo(@RequestParam int id,
                              @RequestParam Date expireDate,
                              @RequestParam int userId){
        boolean result = subscriptionService.updateSubscription(id,expireDate,userId);
        if (result) {
            return "successfully";
        }
        return "unsuccessfully";
    }

    @DeleteMapping("/{id}")
    public String deleteSubscription(@PathVariable int id){
        if (subscriptionService.deleteSubscription(id)) {
            return "successfully";
        }
        return "unsuccessfully";

    }

    @PostMapping("/add/{userId}")
    public String addSubscription(@PathVariable int userId) {
        if (subscriptionService.addSubscription((userId))) {
            return "successfully";
        }
        return "unsuccessfully";
    }


*/

