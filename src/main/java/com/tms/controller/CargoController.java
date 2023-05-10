package com.tms.controller;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import com.tms.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RequestMapping("/cargo")
public class CargoController {

    CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {

        Cargo cargo = cargoService.getCargoById(id);
        model.addAttribute("cargo", cargo);
        return "singleCargo";
    }

    @PostMapping()
    public String createCargo(@RequestParam int userId,
                              @RequestParam int weightCargo,
                              @RequestParam int widthCargo,
                              @RequestParam int lenghtCargo,
                              @RequestParam int hight,
                              @RequestParam String states,
                              @RequestParam String route) {
        boolean result = cargoService.createCargo(userId, weightCargo, widthCargo, lenghtCargo, hight, states, route);
        if (result) {
            return "successfully";
        }
        return "unsuccessfully";
    }

    @PutMapping()
    public String updateCargo(@RequestParam int weightCargo,
                              @RequestParam int widthCargo,
                              @RequestParam int lenghtCargo,
                              @RequestParam int hight,
                              @RequestParam String states,
                              @RequestParam String route,
                              @RequestParam int id,
                              @RequestParam int userId) {
        boolean result = cargoService.updateCargo(weightCargo, widthCargo, lenghtCargo, hight, states, route, id, userId);
        if (result) {
            return "successfully";
        }
        return "unsuccessfully";

    }

    @DeleteMapping("/{id}{userId}")
    public String deleteCargo(@PathVariable int id,
                              @PathVariable int userId) {
        if (cargoService.deleteCargo(id, userId)) {
            return "successfully";
        }
        return "unsuccessfully";
    }

}

















