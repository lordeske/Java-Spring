package com.eske.model;


import com.eske.dto.FastFoodDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;


    private String fullIme;
    private String email;
    private String password;
    private USER_ROLE role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();


    @ElementCollection
    private List<FastFoodDTO> omiljeno = new ArrayList<>();


    @OneToMany(cascade =CascadeType.ALL , orphanRemoval = true)
    private List<Adresa> adresa = new ArrayList<>();








}

