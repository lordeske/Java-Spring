package com.bazapodatak1.database1.repo;

import doomen.Knjige;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnjigaRepo extends CrudRepository<Knjige, String > {
}
