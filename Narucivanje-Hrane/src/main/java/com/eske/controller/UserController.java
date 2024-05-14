package com.eske.controller;


import com.eske.model.User;
import com.eske.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(path = "/profile")
    public ResponseEntity<Optional<User>> findUserByJWTToken(
            @RequestHeader ("Authorization") String jwt
    ) throws Exception {
        Optional<User> user = userService.findUserByJWTToken(jwt);

        return new ResponseEntity<>(user, HttpStatus.OK);



    }
}
