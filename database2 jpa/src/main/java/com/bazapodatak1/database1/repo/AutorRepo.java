package com.bazapodatak1.database1.repo;

import doomen.Autori;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepo extends CrudRepository<Autori, Long>{
}
