package com.eske.controller;

import com.eske.config.JwtProvider;
import com.eske.model.Cart;
import com.eske.model.USER_ROLE;
import com.eske.model.User;
import com.eske.repo.CartRepo;
import com.eske.repo.UserRepo;
import com.eske.req.LoginReq;
import com.eske.response.AuthResponse;
import com.eske.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    private CustomerUserDetailsService customerUserDetailsService;

    private JwtProvider jwtProvider;

    private CartRepo cartRepo;

    @Autowired
    public AuthController(UserRepo userRepo, PasswordEncoder passwordEncoder, CustomerUserDetailsService customerUserDetailsService, JwtProvider jwtProvider, CartRepo cartRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.customerUserDetailsService = customerUserDetailsService;
        this.jwtProvider = jwtProvider;
        this.cartRepo = cartRepo;
    }


    @PostMapping("/singup")
    public ResponseEntity<AuthResponse> createUserHandler(
            @RequestBody User user
            )
    {

        Optional<User> isEmailExist = userRepo.findByEmail(user.getEmail());

        if(isEmailExist.isPresent())
        {
            User createdUser = new User();
            createdUser.setEmail(user.getEmail());
            createdUser.setFullIme(user.getFullIme());
            createdUser.setRole(user.getRole());
            createdUser.setRole(USER_ROLE.valueOf(passwordEncoder.encode(user.getPassword())));


            User savedUser = userRepo.save(createdUser);

            Cart cart = new Cart();
            cart.setCustomer(savedUser);
            cartRepo.save(cart);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user.getEmail(), user.getPassword()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateToken(authentication);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(jwt);
            authResponse.setMessage("Registrovanje uspjesno");
            authResponse.setRole(savedUser.getRole());

            return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

        }
        else
        {
            throw  new UsernameNotFoundException("Korisnik vec postoji");
        }


    }




    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(
            @RequestBody LoginReq loginReq
            )
    {


        String username = loginReq.getEmail();
        String password = loginReq.getPassword();

        Authentication authentication = authenticate(username,password);

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Logovanje uspesno");

        return new ResponseEntity<>(authResponse, HttpStatus.OK);






        
    }

    private Authentication authenticate(String username, String password) {


        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword()))
        {
            throw  new BadCredentialsException("Netacno sifra ili username");


        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

    }

}
