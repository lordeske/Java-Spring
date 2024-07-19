package com.legoFigurice.legoFigurice.Service;


import com.legoFigurice.legoFigurice.Domeni.Figurica;
import com.legoFigurice.legoFigurice.Repo.FiguricaRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.legoFigurice.legoFigurice.Konstante.Konst.SLIKA_LOKACIJA;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor

public class FiguricaService {


    private final FiguricaRepo figuricaRepo;


    public Page<Figurica> getAllFigurice (Integer strana, Integer velicina)
    {

        return  figuricaRepo.findAll(PageRequest.of(strana,velicina, Sort.by("nazivFigurice")));
    }


    public Figurica getFigurica(String id)
    {
        return figuricaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Figurica nije pronadjena"));
    }


    // Kreiranje nove figurice
    public Figurica kreirajFiguricu(Figurica figurica)
    {
        return figuricaRepo.save(figurica);
    }


    // Brisanje figurice
    public void obrisiFiguricu(String idFigurice)
    {
        figuricaRepo.deleteById(idFigurice);
    }

    private final BiFunction<String, MultipartFile, String> slikaFunkcije = (id, slika) -> {
        return slika.getOriginalFilename();
    };



    public String dodajSliku(String idFigurice, MultipartFile file) {
        if (idFigurice == null || idFigurice.isEmpty()) {
            throw new IllegalArgumentException("ID figurice ne može biti prazan.");
        }

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Fajl ne može biti prazan.");
        }

        Figurica figurica;
        try {
            figurica = getFigurica(idFigurice);
        } catch (Exception e) {
            throw new RuntimeException("Greška prilikom preuzimanja figurice sa ID: " + idFigurice, e);
        }

        String nazivSlike;
        try {
            nazivSlike = slikaFunkcije.apply(idFigurice, file);
        } catch (Exception e) {
            throw new RuntimeException("Greška prilikom obrade slike.", e);
        }

        figurica.setUrlSlike(nazivSlike);

        try {
            figuricaRepo.save(figurica);
        } catch (Exception e) {
            throw new RuntimeException("Greška prilikom snimanja figurice.", e);
        }

        return nazivSlike;
    }





}
