package com.legoFigurice.legoFigurice.Repo;

import com.legoFigurice.legoFigurice.Domeni.Figurica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FiguricaRepo extends JpaRepository <Figurica, String> {


        Optional<Figurica> findByidFigurice (String id);
        List<Figurica> findBymaterijalFigurice(String id);


}
