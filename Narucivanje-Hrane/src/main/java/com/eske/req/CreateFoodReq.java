package com.eske.req;

import com.eske.model.Category;
import com.eske.model.IngrediantsItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class CreateFoodReq {


    private Long foodReqID;
    private String foodReqName;
    private String foodReqDescription;
    private Long foodReqPrice;
    private Category foodReqCategory;
    private List<String> foodReqImage;
    private Long fastFoodID;
    private Boolean isVege;
    private List<IngrediantsItem> ingrediantsReq;


}
