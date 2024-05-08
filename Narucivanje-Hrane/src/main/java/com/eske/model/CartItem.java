package com.eske.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CartItemId;

    @ManyToOne
    @JsonIgnore
    private Cart cart;


    @ManyToOne
    private Food food;


    private Integer quantity;

    private List<String> ingredients;

    private Long totalPrice;


}
