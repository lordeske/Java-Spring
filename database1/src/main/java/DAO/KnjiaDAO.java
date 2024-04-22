package DAO;

import doomen.Autori;
import doomen.Knjige;

import java.util.List;
import java.util.Optional;

public interface KnjiaDAO {

    void create(Knjige knjiga);


    Optional<Knjige> find(String s);

    List <Knjige> find();
}
