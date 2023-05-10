package com.tms.controller;

import com.tms.domain.Cargo;
import com.tms.domain.Subscription;
import com.tms.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Component
@RequestMapping("/subscription")
public class SubscriptionController {

    SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
/*

    @GetMapping("/{id}")
    public String getSubscriptionById(@PathVariable int id, Model model){
        Subscription subscription = subscriptionService.getSubscriptionById(id);
        model.addAttribute("subscription", subscription);
        return "singleSubscription";

    }
*/

    @PostMapping()
    public String createSubscription(@RequestParam int userId){
        boolean result = subscriptionService.createSubscription(userId);
        if (result) {
            return "successfully";
        }
        return "unsuccessfully";
    }

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












}
