package com.bazapodatak1.database1.repo;

import doomen.Autori;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepo extends CrudRepository<Autori, Long>{
    Iterable<Autori> godineManjeOd(int godine);

    @Query("SELECT * FROM Autori a where a.godine > ?1")
    Iterable <Autori> nadjiAutoreVeceOd(int i);
}
