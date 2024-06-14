package com.lesson2.Less2.Repo;

import com.lesson2.Less2.Tabele.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {



    public Optional<User> findUserByUsername (String username);


}
