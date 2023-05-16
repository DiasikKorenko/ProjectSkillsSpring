package com.tms.controller;

import com.tms.domain.Cargo;
import com.tms.domain.FavoritesCargo;
import com.tms.domain.FavoritesTransport;
import com.tms.domain.User;
import com.tms.service.*;
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

import java.util.ArrayList;
import java.util.List;

@Component
@RequestMapping("/favorite")
public class FavoriteController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    FavoriteServiceTransport favoriteServiceTransport;
    @Autowired
    FavoriteServiceCargo favoriteServiceCargo;
    @Autowired
    UserService userService;
    @Autowired
    TransportService transportService;
    @Autowired
    CargoService cargoService;

    @Autowired
    public FavoriteController(FavoriteServiceTransport favoriteServiceTransport, FavoriteServiceCargo favoriteServiceCargo) {
        this.favoriteServiceTransport = favoriteServiceTransport;
        this.favoriteServiceCargo = favoriteServiceCargo;

    }

    @Operation(summary = "Добавление транспорта в избранное")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @PostMapping
    public ResponseEntity<HttpStatus> addTransport(int userId, int transportId) {

        FavoritesTransport ft = favoriteServiceTransport.addTransport(userId,transportId);
        if (ft == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "Удаление из избранного по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер,транспорт удален из избранного!"),
            @ApiResponse(responseCode = "400", description = "Не получилось удалить транспорт..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTransport(@PathVariable int id) {
        favoriteServiceTransport.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    @GetMapping
    public ResponseEntity<FavoritesTransport> findAllByUserId(int userId) {
        List<FavoritesTransport> list = favoriteServiceTransport.findAllByUserId(userId);
        return new ResponseEntity<>(list, (!list.isEmpty()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    }







   /* @GetMapping("/transport/alltransport")
    public String FavTransport(@RequestParam("id") int id, Model model) {
        favoriteServiceTransport.addTransport(userService.getCurrentUser().getId(), id);
        model.addAttribute("transport", transportService.getTransportById(id));
        return "addedToFavouriteTransport";
    }

    @GetMapping("/cargo/allcargo")
    public String FavCargo(@RequestParam("id") int id, Model model) {
        favoriteServiceCargo.addCargo(userService.getCurrentUser().getId(), id);
        model.addAttribute("cargo", cargoService.getCargoById(id));
        return "addedToFavouriteCargo";
    }

    @GetMapping()
    public String showFavourites(Model model) {
        List<FavoritesCargo> cargos = favoriteServiceCargo.findAllByUserId(userService.getCurrentUser().getId());
        List<FavoritesTransport> trancports = favoriteServiceTransport.findAllByUserId(userService.getCurrentUser().getId());
        model.addAttribute("cargos", cargos);
        model.addAttribute("transports", trancports);
        model.addAttribute("cargosInf", cargoService.findCargosInf(cargos));
        model.addAttribute("transportsInf", transportService.findTransportsInf(trancports));
        return "favourites";
    }

    @GetMapping("/delete/transport")
    public String delTransport(@RequestParam("id") int id) {
        favoriteServiceTransport.deleteById(id);
        return "redirect:/favorite";
    }

    @GetMapping("/delete/cargo")
    public String delCargo(@RequestParam("id") int id) {
        favoriteServiceCargo.deleteById(id);
        return "redirect:/favorite";
    }*/

