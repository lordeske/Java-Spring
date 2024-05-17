package com.eske.repo;

import com.eske.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {


       List<Food> findByFastFoodID(Long fastFoodID);



       /// SAMO TRAZENJE PO IMENU!!!!
       @Query("SELECT f FROM Food f WHERE f.foodName LIKE %:keyword%")
       List<Food> searchFood(@Param("keyword") String keyword);




}
