package com.eske.controller;

import com.eske.config.UnauthorizedException;
import com.eske.model.FastFood;
import com.eske.model.User;
import com.eske.req.CreateFastFoodREQ;
import com.eske.service.FastFoodService;
import com.eske.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/admin/fastfood")
public class AdminFastFoodController {


    private final UserService userService;


    private final FastFoodService fastFoodService;

    @Autowired
    public AdminFastFoodController(UserService userService, FastFoodService fastFoodService) {
        this.userService = userService;
        this.fastFoodService = fastFoodService;
    }


     @PostMapping()
     public ResponseEntity<FastFood> createFastFood(
             @RequestBody CreateFastFoodREQ req,
             @RequestHeader("Authorization") String jwt
             ) throws Exception {

         Optional<User> userOptional = userService.findUserByJWTToken(jwt) ;

         if(userOptional.isPresent())
         {
             User user = userOptional.get();

             FastFood fastFood = fastFoodService.createFastFood(req,user);

             return new ResponseEntity<>(fastFood, HttpStatus.CREATED);

         }
         else
         {
             throw new UnauthorizedException("Unauthorized");

         }



     }



     @PutMapping(path ="/{id}")
     public ResponseEntity<FastFood> updateFastFood(
             @RequestBody CreateFastFoodREQ req,
             @PathVariable Long id,
             @RequestHeader ("Authorization") String jwt
     ) throws Exception {

         Optional<User> userOptional = userService.findUserByJWTToken(jwt);

         if(userOptional.isPresent())
         {
             User user = userOptional.get();
             FastFood fastFood  = fastFoodService.updateFastFood(id,req);

             return new ResponseEntity<>(fastFood,HttpStatus.CREATED);

         }
         else
         {
             throw new UnauthorizedException("Ne valja token");
         }


     }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteFastFood(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Optional<User> userOptional = userService.findUserByJWTToken(jwt);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            fastFoodService.deleteFastFood(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new UnauthorizedException("Ne valja token");
        }
    }


    @PutMapping(path = "/{id}/status")
    public ResponseEntity<FastFood> updateFastFoodStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {

        Optional<User> userOptional = userService.findUserByJWTToken(jwt);
        if (userOptional.isPresent())
        {
            User user = userOptional.get();
            FastFood fastFood = fastFoodService.upadateFastFoodStatus(id);

            return  new ResponseEntity<>(fastFood,HttpStatus.CREATED);

        }
        else
        {
            throw new UnauthorizedException("Ne valja token");
        }

    }



    @GetMapping(path = "/user")
    public ResponseEntity<FastFood> findFastFoodByUserId(
            @RequestHeader ("Authorization") String jwt
    ) throws Exception {
        Optional<User> userOptional = userService.findUserByJWTToken(jwt);
        if (userOptional.isPresent())
        {
            User user = userOptional.get();

            FastFood fastFood = fastFoodService.getFastFoodBYUserId(user.getUserID());

            return  new ResponseEntity<>(fastFood,HttpStatus.CREATED);

        }
        else
        {
            throw new UnauthorizedException("Ne valja token");
        }


    }





}
