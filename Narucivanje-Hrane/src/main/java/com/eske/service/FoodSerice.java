package com.eske.service;

import com.eske.model.Category;
import com.eske.model.FastFood;
import com.eske.model.Food;
import com.eske.req.CreateFoodReq;

import java.util.List;

public interface FoodSerice {



        public Food createFood(CreateFoodReq req, Category category, FastFood fastFood);

        public Void deleteFood(Long foodID);

        public List<Food> getFoodFromFastFood(Long fastFoodID, boolean isVege, String foodCategory);


        public List<Food> searchFood(String keyword);

        public Food findFoodByID(Long FoodID);

        public Food updateFoodAvail(Long FoodID);







}
