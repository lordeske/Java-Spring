package com.eske.controller;

import com.eske.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    private



}
