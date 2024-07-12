package com.legoFigurice.legoFigurice.Kontroleri;

import com.legoFigurice.legoFigurice.Domeni.Figurica;
import com.legoFigurice.legoFigurice.Service.FiguricaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.legoFigurice.legoFigurice.Konstante.Konst.SLIKA_LOKACIJA;

@RestController
@RequestMapping("/figurice")
@RequiredArgsConstructor
public class FiguriceKontroler {

    private final FiguricaService figuricaService;

    @PostMapping
    public ResponseEntity<Figurica> kreirajFiguricu(@RequestBody Figurica figurica) {
        return ResponseEntity.created(URI.create("/figurice/idFigurice")).body(figuricaService.kreirajFiguricu(figurica));
    }

    @GetMapping
    public ResponseEntity<Page<Figurica>> dobiSveFigurice(@RequestParam(value = "strana", defaultValue = "0") Integer strana,
                                                          @RequestParam(value = "velicina", defaultValue = "10") Integer velicina) {
        return ResponseEntity.ok().body(figuricaService.getAllFigurice(strana, velicina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Figurica> dobijFiguricu (@RequestParam (value = "id") String id)
    {
        return ResponseEntity.ok().body(figuricaService.getFigurica(id));
    }

    @PutMapping("/slika")
    public ResponseEntity<String> uploadSliku(@RequestParam("id") String idFigurice,
                                              @RequestParam("fajl")MultipartFile file)
    {
        return ResponseEntity.ok().body(figuricaService.dodajSliku(idFigurice,file));
    }

    @GetMapping(path = "/fotografija/{filename}")
    public byte[] dobijSliku(@PathVariable ("filename") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(SLIKA_LOKACIJA+ fileName));
    }


}
