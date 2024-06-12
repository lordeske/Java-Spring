package com.eske.FilmoviAPI.Baza;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recenzije")
@NoArgsConstructor
@AllArgsConstructor

public class Recenzija {

    private ObjectId id;
    private String tijelo;


}
