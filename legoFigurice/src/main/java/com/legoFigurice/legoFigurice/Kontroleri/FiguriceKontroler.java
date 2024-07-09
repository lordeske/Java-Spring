package com.legoFigurice.legoFigurice.Kontroleri;


import com.legoFigurice.legoFigurice.Domeni.Figurica;
import com.legoFigurice.legoFigurice.Service.FiguricaService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/figurice")
@RequiredArgsConstructor
public class FiguriceKontroler {

    private final FiguricaService figuricaService;


    @PostMapping
    public ResponseEntity<Figurica> kreirajFiguricu(@RequestBody Figurica figurica)
    {

        return ResponseEntity.created(URI.create("/figurice/{idFigurice}")).body(figuricaService.kreirajFiguricu(figurica));
    }


    @GetMapping
    public ResponseEntity<Page<Figurica>> getFiguricu (@RequestParam (value = "page", defaultValue = "0") Integer strana,
                                                       @RequestParam (value = "velicina" , defaultValue = "10") Integer velicina)
    {


        return ResponseEntity.ok().body(figuricaService.getAllFigurice(strana,velicina))
    }



}
