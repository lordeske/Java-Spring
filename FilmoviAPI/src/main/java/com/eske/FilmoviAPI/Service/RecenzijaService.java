package com.eske.FilmoviAPI.Service;


import com.eske.FilmoviAPI.Baza.Recenzija;
import com.eske.FilmoviAPI.Repo.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecenzijaService {

    @Autowired
    private FilmRepo filmRepo;



    public Recenzija kreirajRecenziju()
    {
        Recenzija recenzija = new Recenzija();
        
    }



}
