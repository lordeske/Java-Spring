
package com.bazapodatak1.database1.dao.repo;

import DAO.impl.AutorDAOImpl;
import DAO.impl.KnjigaDAOImpl;
import com.bazapodatak1.database1.dao.TestDataUtil;
import com.bazapodatak1.database1.repo.AutorRepo;
import com.bazapodatak1.database1.repo.KnjigaRepo;
import doomen.Autori;
import doomen.Knjige;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class KnjigeRepoIntegraTest {

    private KnjigaRepo underTest;


    @Autowired
    public KnjigeRepoIntegraTest(KnjigaRepo underTest) {
        this.underTest = underTest;

    }


    @Test
    public void testThatKnigaCanBeCreated() {
        Autori autori = TestDataUtil.createTestAutor();
        Knjige knjige = TestDataUtil.createTestKnjiga(autori);


        underTest.save(knjige);

        Optional<Knjige> result = underTest.findById(knjige.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(knjige);
    }
}

/*
    @Test
    public void testThatKnigeMoguBitiKreiraneIVracenee()
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


    @Test
    public void testKojiUpdatujeKnjie()
    {

        Autori autori = TestDataUtil.createTestAutor();
        autorDAO.create(autori);

        Knjige knjiga1= TestDataUtil.createTestKnjigaA();
        knjiga1.setAutor_id(autori.getId());
        underTest.create(knjiga1);

        knjiga1.setNaslov("Mali Mrav");
        underTest.update(knjiga1.getIsbn(), knjiga1);

        Optional<Knjige> reslt = underTest.findOne(knjiga1.getIsbn());
        assertThat(reslt).isPresent();
        assertThat(reslt).get().isEqualTo(knjiga1);






    }



    @Test
    public void testKojiBriseIzBaze()
    {

        Autori autori = TestDataUtil.createTestAutor();
        autorDAO.create(autori);

        Knjige knjiga1= TestDataUtil.createTestKnjigaA();
        knjiga1.setAutor_id(autori.getId());
        underTest.create(knjiga1);


        underTest.delete(knjiga1.getIsbn());


        Optional <Knjige> result= underTest.findOne(knjiga1.getIsbn());

        assertThat(result)
                .isEmpty();

        assertThat(result).isEmpty();


    }





}


 */
