package com.eske.FilmoviAPI.Kontroleri;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/filmovi")
public class FIlmoviKontroler {



    @GetMapping
    public String sviFilmovi ()
    {

        return "Svi filmovi";
    }


}
