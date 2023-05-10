package com.tms.controller;

import com.tms.domain.Cargo;
import com.tms.domain.Transport;
import com.tms.service.FavoriteService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Component
@RequestMapping("/favorite")
public class FavoriteController {

    FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/add")
    public String add(@RequestParam int userId,
                      @RequestParam(required = false,defaultValue = "0") int transportId,
                      @RequestParam (required = false,defaultValue = "0") int cargoId) {
        boolean result = favoriteService.add(userId,transportId,cargoId);
        if (result) {
            return "successfully";
        }
        return "unsuccessfully";
    }

    @GetMapping("/giveMyFavorite")
    public String giveAll(@RequestParam int userId, Model model) {

        ArrayList<Transport> transportList = favoriteService.getTransport(userId);
        model.addAttribute("transportList", transportList.toString());

        ArrayList<Cargo> cargoList = favoriteService.getCargo(userId);
        model.addAttribute("cargoList", cargoList.toString());

        return "allTransportUserFavorit";


    }
}
