package com.eske.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Food {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long foodID;

    private String foodName;
    private String foodDescription;
    private Long foodPrice;


    @ManyToOne
    private Category foodCategory;

    @Column(length = 1000)
    @ElementCollection
    private List<String> foodImage;
    private Boolean  available;

    @ManyToOne
    private FastFood fastFood;

    private Boolean isVege;


    @ManyToMany
    private List<IngrediantsItem> ingrediants = new ArrayList<>();










}
