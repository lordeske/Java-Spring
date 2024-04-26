package com.example.SpringWeb.kontoleri;


import com.example.SpringWeb.domen.Knjiga;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class KnjigaKontoler {


    @GetMapping(path = "/knjige")
    public Knjiga vratiKnjigu()
    {

        return Knjiga.builder()
                .isbn(978-0-13-47627-5)
                .godinaIzdavanja("2002")
                .ime("Avlijski glupan")
                .autor("Mali Mujo")
                .build();


    }

    @PostMapping(path = "/knjige")
    public Knjiga napraviKnjigu(@RequestBody final Knjiga knjiga)
    {
        log.info("Dobio si knjigu" + knjiga.toString());
        return knjiga;
    }





}
