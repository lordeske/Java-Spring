package com.eske.repo;

import com.eske.model.FastFood;
import com.eske.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FastFoodRepo extends JpaRepository<FastFood, Long> {



    @Query("SELECT r FROM FastFood r WHERE lower(r.name) LIKE lower(concat('%', :query, '%'))")
    FastFood findBySearchQuery(String query);
    FastFood searchFastFOod(String name);




}
