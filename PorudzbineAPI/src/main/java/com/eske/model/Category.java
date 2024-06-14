package com.eske.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryID;

    private String  categoryName;


    @ManyToOne
    @JsonIgnore

    private FastFood fastFood;


}
