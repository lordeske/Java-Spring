package com.eske.FilmoviAPI.Service;


import com.eske.FilmoviAPI.Baza.Film;
import com.eske.FilmoviAPI.Repo.FilmRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepo filmRepo;





    public List<Film> getSviFilmovi()
    {


        return   filmRepo.findAll();
    }

    public Optional<Film> getFilm(String id)
    {


        return filmRepo.getFilmByImdbId(id);
    }


}
