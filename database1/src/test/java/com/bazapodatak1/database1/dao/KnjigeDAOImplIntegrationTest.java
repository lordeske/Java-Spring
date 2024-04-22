package com.bazapodatak1.database1.dao;

import DAO.impl.AutorDAOImpl;
import DAO.impl.KnjigaDAOImpl;
import doomen.Autori;
import doomen.Knjige;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
public class KnjigeDAOImplIntegrationTest {

    private KnjigaDAOImpl underTest;
    private AutorDAOImpl autorDAO;

    @Autowired
    public KnjigeDAOImplIntegrationTest(KnjigaDAOImpl underTest, AutorDAOImpl autorDAO) {
        this.underTest = underTest;
        this.autorDAO = autorDAO;
    }


    @Test
    public void testThatKnigaCanBeCreated()
    {
        Autori autori = TestDataUtil.createTestAutor();
        autorDAO.create(autori);


        Knjige knjige = TestDataUtil.createTestKnjiga();
        knjige.setAutor_id(autori.getId());
        underTest.create(knjige);

        Optional<Knjige> result = underTest.find(knjige.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(knjige);
    }


    @Test
    public void testThatKnigeMoguBiti()
    {
        Autori autori = TestDataUtil.createTestAutor();
        autorDAO.create(autori);

        Knjige knjiga1= TestDataUtil.createTestKnjigaA();
        knjiga1.setAutor_id(autori.getId());
        underTest.create(knjiga1);

        Knjige knjiga2= TestDataUtil.createTestKnjigaA();
        knjiga2.setAutor_id(autori.getId());
        underTest.create(knjiga2);

        Knjige knjiga3= TestDataUtil.createTestKnjigaA();
        knjiga2.setAutor_id(autori.getId());
        underTest.create(knjiga3);


        List<Knjige> result = underTest.find();

        assertThat(result).hasSize(3)
                .contains(knjiga1,knjiga2,knjiga3);






    }


}
