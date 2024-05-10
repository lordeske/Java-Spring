package com.eske.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long OrderItemID;


    @ManyToOne
    private Food food;

    private Integer quantity;
    private Long totalPrice;
    private List<String> Ingrediants;
    private String openingHours;




}
