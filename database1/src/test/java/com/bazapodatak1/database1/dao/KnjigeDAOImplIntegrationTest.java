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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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

}
