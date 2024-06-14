package com.eske.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IngrediantsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IngrediantsItemID;

    private String IngrediantsItemName;


    @ManyToOne
    private IngrediantsItemCategory IngrediantsItemCategory;

    @ManyToOne
    @JsonIgnore
    private FastFood fastFood;

    private Boolean inStoke = true;


}
