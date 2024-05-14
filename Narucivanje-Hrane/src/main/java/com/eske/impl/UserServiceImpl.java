package com.eske.impl;

import com.eske.config.JwtProvider;
import com.eske.model.User;
import com.eske.repo.UserRepo;
import com.eske.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public Optional<User> findUserByJWTToken(String jwt) throws Exception {

      String email =   jwtProvider.getEmailFromJWTTOken(jwt);

      Optional<User> user = findUserByEmail(email);

      if(!user.isPresent())
      {
          throw new UsernameNotFoundException("Korisnik sa tim tokenom ne postoji");
      }

      return user;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws Exception {
        Optional<User> user = userRepo.findByEmail(email);

        if(!user.isPresent())
        {
            throw new UserNotFoundException("Korisnik s e-mailom " + email + " ne postoji");

        }
        return  user;

    }

    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
