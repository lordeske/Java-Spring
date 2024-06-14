package com.eske.service;

import com.eske.model.User;

import java.util.Optional;

public interface UserService {



        public Optional<User> findUserByJWTToken(String jwt) throws Exception;

        public Optional<User> findUserByEmail(String email) throws Exception;




}
