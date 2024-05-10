package com.eske.service;

import com.eske.model.USER_ROLE;
import com.eske.model.User;
import com.eske.repo.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    public CustomerUserDetailsService(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findByEmail(username);

        User USER = userOptional.orElseThrow(() -> new UsernameNotFoundException("Nije pronađen korisnik sa mejlom " + username));

        USER_ROLE role = USER.getRole();

        if (role == null) {
            role = USER_ROLE.ROLE_CUSTOMER;
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(USER.getEmail(), USER.getPassword(), authorities);
    }

}
