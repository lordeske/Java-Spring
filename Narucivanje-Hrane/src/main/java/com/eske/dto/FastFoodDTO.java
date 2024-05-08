package com.eske.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Embeddable
public class FastFoodDTO {


    private String ime;

    @Column(length = 1000)
    private List<String> slike;

    private String opis;
    private Long fastFoodID;
}
