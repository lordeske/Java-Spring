package com.eske.service;

import com.eske.dto.FastFoodDTO;
import com.eske.model.FastFood;
import com.eske.model.User;
import com.eske.req.CreateFastFoodREQ;

import java.util.List;

public interface FastFoodService {


    public FastFood createFastFood(CreateFastFoodREQ req, User user);


    public  FastFood updateFastFood(Long fastFoodID , CreateFastFoodREQ updatedFastFood);



    public void deleteFastFood(Long fastFoodID);

    public List<FastFood> allFastFoods ();

    public  List<FastFood> searchFastFood(String FastFoodName);
    public  List<FastFood> searchFastFoodID(Long fastFoodID);


    public FastFood getFastFoodBYUserId(Long UserID);


    public FastFoodDTO addToFav(Long fastFoodID, User user);

    public FastFood upadateFastFoodStatus(Long id);







}
