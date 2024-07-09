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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.BiFunction;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class FiguricaService {


    private final FiguricaRepo figuricaRepo;


    public Page<Figurica> getAllFigurice (Integer strana, Integer velicina)
    {

        return  figuricaRepo.findAll(PageRequest.of(strana,velicina, Sort.by("imeFigurice")));
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
    public void obrisiFiguricu(Figurica figurica)
    {
        figuricaRepo.delete(figurica);
    }


    public String dodajSliku(String idFigurice, MultipartFile file)
    {
        Figurica figurica = getFigurica(idFigurice);

        String urlSlike = null;

        figurica.setUrlSlike(urlSlike);

        figuricaRepo.save(figurica);

        return urlSlike;
    }

    private final BiFunction<String,MultipartFile, String> slikaFunkcije = (id,Slika) -> {
        try
        {
            Path lokacijaSlike = Paths.get("").toAbsolutePath().normalize(); // Dobijanje lokacije

            if (!Files.exists(lokacijaSlike))  // Da li postoji
            {
                Files.createDirectories(lokacijaSlike);
            }
        }
        catch (Exception e)
        {
                throw new RuntimeException("Nije moguce sacuvati sliku")
        }
    }


}
