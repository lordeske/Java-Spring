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


    public String dodajSliku(String idFigurice, MultipartFile file)
    {
        Figurica figurica = getFigurica(idFigurice);

        String urlSlike = slikaFunkcije.apply(idFigurice, file);

        figurica.setUrlSlike(urlSlike);

        figuricaRepo.save(figurica);

        return urlSlike;
    }

    private  final Function<String, String> ekstenzijaFajla = imeFajla ->   /// Dobijanje ekstenzije
            Optional.of(imeFajla).filter(ime -> ime.contains("."))
                    .map(ime -> "." + ime.substring(imeFajla.lastIndexOf(".")+ 1 )).orElse(".jpg");

    private final BiFunction<String, MultipartFile, String> slikaFunkcije = (id, slika) -> {
        String imeFajla = id + ekstenzijaFajla.apply(slika.getOriginalFilename());

        try {
            Path lokacijaSlike = Paths.get(SLIKA_LOKACIJA).toAbsolutePath().normalize(); // Dobijanje lokacije

            if (!Files.exists(lokacijaSlike)) {  // Da li postoji
                Files.createDirectories(lokacijaSlike);
            }

            Path ciljnaPutanja = lokacijaSlike.resolve(imeFajla);
            Files.copy(slika.getInputStream(), ciljnaPutanja, StandardCopyOption.REPLACE_EXISTING);

            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/figurice/slika/" + imeFajla).toUriString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Nije moguće sačuvati sliku: " + e.getMessage());
        }
    };




}
