package DAO;

import doomen.Autori;

import java.util.List;
import java.util.Optional;

public interface AutorDAO {



    public void create(Autori autori);

    Optional<Autori> findOne(long l);

    List<Autori> find();
}
