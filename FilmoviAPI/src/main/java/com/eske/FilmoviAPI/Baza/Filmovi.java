package com.eske.FilmoviAPI.Baza;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collation = "filmovi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filmovi {

    @Id
    private ObjectId _id;
    private String imdbId;

    private String naslov;
    private String datumIzlaska;
    private String linkTrejlera;
    private List<String> zanrovi;
    private String poster;
    private List<String> pozadine;

    @DocumentReference()
    private List<Recenzija> idRecenzija;







}
