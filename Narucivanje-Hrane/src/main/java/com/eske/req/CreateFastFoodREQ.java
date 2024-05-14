package com.eske.req;


import com.eske.model.Adresa;
import com.eske.model.ContactInfo;
import lombok.Data;

import java.util.List;

@Data
public class CreateFastFoodREQ {

    private Long id;
    private String ime;
    private String descrption;
    private Adresa adresa;
    private ContactInfo contactInfo;
    private List<String> images;



}
