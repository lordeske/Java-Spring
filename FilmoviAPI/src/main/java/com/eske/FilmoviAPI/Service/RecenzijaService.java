package com.eske.FilmoviAPI.Service;


import com.eske.FilmoviAPI.Baza.Film;
import com.eske.FilmoviAPI.Baza.Recenzija;
import com.eske.FilmoviAPI.Repo.FilmRepo;
import com.eske.FilmoviAPI.Repo.RecenzijaRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepo recenzijaRepo;


    @Autowired
    private MongoTemplate mongoTemplate;

    public Recenzija kreirajRecenziju(String komentar, String ImdbID)
    {
        Recenzija recenzija = recenzijaRepo.insert(new Recenzija(komentar));

        mongoTemplate.update(Film.class)
                .matching(Criteria.where("imdbId").is(ImdbID))
                .apply(new Update().push("idRecenzija").value(recenzija)).first();


        return recenzija;
    }



}
