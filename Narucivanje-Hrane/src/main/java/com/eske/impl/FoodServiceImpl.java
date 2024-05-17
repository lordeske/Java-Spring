package com.eske.impl;

import com.eske.model.Category;
import com.eske.model.FastFood;
import com.eske.model.Food;
import com.eske.repo.FoodRepo;
import com.eske.req.CreateFoodReq;
import com.eske.service.FoodSerice;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FoodServiceImpl implements FoodSerice {

    @Autowired
    private FoodRepo foodRepo;




    @Override
    public Food createFood(CreateFoodReq req, Category category, FastFood fastFood) {
        Food food = new Food();

        food.setFoodCategory(category);
        food.setFastFood(fastFood);
        food.setFoodName(req.getFoodReqName());
        food.setFoodImage(req.getFoodReqImage());
        food.setFoodPrice(req.getFoodReqPrice());
        food.setIngrediants(req.getIngrediantsReq());
        food.setIsVege(req.getIsVege());
        food.setFoodDescription(req.getFoodReqDescription());

        fastFood.getFoods().add(food);


        return foodRepo.save(food);


    }

    @Override
    public void deleteFood(Long foodID) {


       Optional<Food> foodFoodOptional= foodRepo.findById(foodID);

       if(foodFoodOptional.isPresent())
       {
           Food food = foodFoodOptional.get();
           food.setFastFood(null);

           foodRepo.save(food);
       }


    }

    @Override
    public List<Food> getFoodFromFastFood(Long fastFoodID
            , boolean isVege,
                                          String foodCategory) {


        List<Food> foods = foodRepo.findByFastFoodID(fastFoodID);


        if(isVege)
        {
            foods = filterByVege(foods,isVege);

        }

        if(foodCategory != null && !foodCategory.equals(""))
        {
            foods = filterByCategory(foods, foodCategory);
        }

        return foods;



    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {

        return foods.stream().filter(food -> {
            if(food.getFoodCategory() != null)
            {
                return food.getFoodCategory().getCategoryName().equals(foodCategory);
            }

            return false;
        }).collect(Collectors.toList());

    }



    //// MALO PROMIJENITI LOGIKU!!!
    private List<Food> filterByVege(List<Food> foods, boolean isVege) {


        return  foods.stream().filter(food -> food.getIsVege() == isVege)
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepo.searchFood(keyword);
    }

    @Override
    public Food findFoodByID(Long FoodID) {
        Optional<Food> foodOptional = foodRepo.findById(FoodID);

        if(foodOptional.isPresent())
        {
            Food food = foodOptional.get();
            return food;
        }
        else
        {
            throw new EntityNotFoundException("Ne postoji ta hrana");
        }
    }

    @Override
    public Food updateFoodAvail(Long FoodID) {
        Food food = findFoodByID(FoodID);

        food.setAvailable(!food.getAvailable());

        foodRepo.save(food);

        return food;
    }
}
