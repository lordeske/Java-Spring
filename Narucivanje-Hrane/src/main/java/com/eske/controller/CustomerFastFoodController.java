package com.eske.controller;


import com.eske.config.UnauthorizedException;
import com.eske.dto.FastFoodDTO;
import com.eske.model.FastFood;
import com.eske.model.User;
import com.eske.service.FastFoodService;
import com.eske.service.UserService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/fastfood")
public class CustomerFastFoodController {




    private final UserService userService;
    private final FastFoodService fastFoodService;

    @Autowired
    public CustomerFastFoodController(UserService userService, FastFoodService fastFoodService) {
        this.userService = userService;
        this.fastFoodService = fastFoodService;
    }



    @GetMapping(path = "/search")
    public ResponseEntity<List<FastFood>> searchFastFood(
        @RequestHeader("Authorization") String jwt,
        @RequestParam String keyword
    ) throws Exception {
        Optional<User> userOptional = userService.findUserByJWTToken(jwt);

        if(userOptional.isPresent())
        {
            User user = userOptional.get();

            List<FastFood>  fastFoods = fastFoodService.searchFastFood(keyword);

            return new ResponseEntity<>(fastFoods, HttpStatus.OK);

        }
        else
        {
            throw  new UnauthorizedException("Ne valja token ");
        }

    }

    @GetMapping()
    public ResponseEntity<List<FastFood>> getAllFastFoods(
            @RequestHeader("Authorization") String jwt

    ) throws Exception {
        Optional<User> userOptional = userService.findUserByJWTToken(jwt);

        if(userOptional.isPresent())
        {
            User user = userOptional.get();

            List<FastFood>  fastFoods = fastFoodService.allFastFoods();

            return new ResponseEntity<>(fastFoods, HttpStatus.OK);

        }
        else
        {
            throw  new UnauthorizedException("Ne valja token ");
        }

    }



    @GetMapping(path = "/{id}")
    public ResponseEntity<FastFood> findFastFoodByID(
            @RequestHeader("Authorization") String jwt,
            @RequestParam Long id

    ) throws Exception {
        Optional<User> userOptional = userService.findUserByJWTToken(jwt);

        if(userOptional.isPresent())
        {
            User user = userOptional.get();

            FastFood fastFood = fastFoodService.searchFastFoodID(id);

            return new ResponseEntity<>(fastFood, HttpStatus.OK);

        }
        else
        {
            throw  new UnauthorizedException("Ne valja token ");
        }

    }


    @PutMapping(path = "/{id}/add-omiljeno")
    public ResponseEntity<FastFoodDTO> addToOmiljeno(
            @RequestHeader("Authorization") String jwt,
            @RequestParam Long id

    ) throws Exception {
        Optional<User> userOptional = userService.findUserByJWTToken(jwt);

        if(userOptional.isPresent())
        {
            User user = userOptional.get();

            FastFoodDTO fastFood = fastFoodService.addToFav(id,user);

            return new ResponseEntity<>(fastFood, HttpStatus.OK);

        }
        else
        {
            throw  new UnauthorizedException("Ne valja token ");
        }

    }


}
