package com.eske.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long OrderID;


    @ManyToOne
    private User customer;


    @ManyToOne
    @JsonIgnore
    private FastFood fastFood;


    private Long totalAmount;
    private String orderStatus;
    private Date createdDate;

    @ManyToOne
    private Adresa deliveryAdress;

    @OneToMany
    private List<OrderItem> items;


    private Integer totalItem;

    private Integer totalPrice;

    //private Payment paymentMethod;


}
