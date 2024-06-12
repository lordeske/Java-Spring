package com.eske.FilmoviAPI.Kontroleri;

import com.eske.FilmoviAPI.Baza.Recenzija;
import com.eske.FilmoviAPI.Service.RecenzijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/recenzije")
public class RecenzijaKontroler {

    @Autowired
    private RecenzijaService recenzijaService;


    @PostMapping
    public ResponseEntity<Recenzija> dodajRecenziju(
            @RequestBody Map<String, String> varijabla
    ) {


        return new ResponseEntity<Recenzija>(recenzijaService.kreirajRecenziju(
                varijabla.get("komentar"), varijabla.get("ImdbId")), HttpStatus.CREATED);

    }


}
