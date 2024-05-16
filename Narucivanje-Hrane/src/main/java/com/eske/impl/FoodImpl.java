package com.eske.impl;

import com.eske.model.Category;
import com.eske.model.FastFood;
import com.eske.model.Food;
import com.eske.req.CreateFoodReq;
import com.eske.service.FoodSerice;

import java.util.List;

public class FoodImpl implements FoodSerice {
    @Override
    public Food createFood(CreateFoodReq req, Category category, FastFood fastFood) {
        return null;
    }

    @Override
    public Void deleteFood(Long foodID) {
        return null;
    }

    @Override
    public List<Food> getFoodFromFastFood(Long fastFoodID, boolean isVege, String foodCategory) {
        return null;
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return null;
    }

    @Override
    public Food findFoodByID(Long FoodID) {
        return null;
    }

    @Override
    public Food updateFoodAvail(Long FoodID) {
        return null;
    }
}
