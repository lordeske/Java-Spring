package com.eske.FilmoviAPI.Kontroleri;


import com.eske.FilmoviAPI.Baza.Film;
import com.eske.FilmoviAPI.Service.FilmService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/filmovi")
public class FIlmoviKontroler {


    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<Film>> sviFilmovi ()
    {
       return new ResponseEntity<List<Film>>(filmService.getSviFilmovi(), HttpStatus.OK);



    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Film>> getFilm (
            @PathVariable String id
            )
    {

        return  new ResponseEntity<Optional<Film>>(filmService.getFilm(id), HttpStatus.OK);
    }





}
