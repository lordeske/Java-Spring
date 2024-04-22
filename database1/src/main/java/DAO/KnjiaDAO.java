package DAO;

import doomen.Autori;
import doomen.Knjige;

import java.util.List;
import java.util.Optional;

public interface KnjiaDAO {

    void create(Knjige knjiga);


    Optional<Knjige> findOne(String s);

    List <Knjige> find();

    void update(String l,Knjige knjige);


    void delete(String s);
}
