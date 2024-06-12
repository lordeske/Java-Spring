package com.eske.FilmoviAPI.Repo;

import com.eske.FilmoviAPI.Baza.Film;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepo extends MongoRepository<Film, ObjectId> {



    Optional<Film> getFilmByImdbId(String imdbId);



}
