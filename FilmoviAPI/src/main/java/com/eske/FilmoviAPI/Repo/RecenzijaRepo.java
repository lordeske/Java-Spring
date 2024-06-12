package com.eske.FilmoviAPI.Repo;

import com.eske.FilmoviAPI.Baza.Recenzija;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecenzijaRepo extends MongoRepository<Recenzija, ObjectId> {
}
