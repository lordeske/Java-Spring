package com.eske.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngrediantsItemCategory {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long IngrediantsItemCategoryID;

        private String IngrediantsItemCategoryName;


        @ManyToOne
        @JsonIgnore
        private FastFood fastFood;

        @OneToMany(mappedBy = "IngrediantsItemCategory", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<IngrediantsItem> ingrediants = new ArrayList<>();



}
