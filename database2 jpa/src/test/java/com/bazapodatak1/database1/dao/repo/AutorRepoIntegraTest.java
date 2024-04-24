
package com.bazapodatak1.database1.dao.repo;


import com.bazapodatak1.database1.dao.TestDataUtil;
import com.bazapodatak1.database1.repo.AutorRepo;
import doomen.Autori;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.useDefaultDateFormatsOnly;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD )
public class AutorRepoIntegraTest {



    private final AutorRepo underTest;

    @Autowired
    public AutorRepoIntegraTest(AutorRepo underTest) {
        this.underTest = underTest;
    }


    @Test
    public void testThatAutorIsCreated() {
        Autori autor = TestDataUtil.createTestAutor();
        underTest.save(autor);

        Optional<Autori> result = underTest.findById(autor.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(autor);
    }


    @Test
    public void testThatMultyAutorsCanBeCreatedAndCalled()
    {
        Autori autoriA = TestDataUtil.createTestAutorA() ;
        underTest.save(autoriA);
        Autori autoriB = TestDataUtil.createTestAutorB() ;
        underTest.save(autoriB);
        Autori autoriC = TestDataUtil.createTestAutorC() ;
        underTest.save(autoriC);


        Iterable<Autori> autoriLista = underTest.findAll();
        assertThat(autoriLista).hasSize
                (3).contains(autoriA,autoriB,autoriC);

    }


    @Test
    public void testThatAutorIsUp()
    {
        Autori autoriA = TestDataUtil.createTestAutorA() ;
        underTest.save(autoriA);

        autoriA.setIme("Milovan");
        underTest.save(autoriA);

        Optional <Autori> result  = underTest.findById(autoriA.getId());

        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(autoriA);



    }


    @Test
    public void testAutorDelete()
    {
        Autori autoriA = TestDataUtil.createTestAutorA() ;
        underTest.save(autoriA);

        underTest.deleteById(autoriA.getId());

         Optional <Autori> result = underTest.findById(autoriA.getId());
         assertThat(result).isEmpty();


    }

    @Test
    public void TestKojiDobijaAutoreGodinaManjeeOd()
    {

        Autori autor = TestDataUtil.createTestAutor();
        underTest.save(autor);
        Autori autor1 = TestDataUtil.createTestAutorB();
        underTest.save(autor1);
        Autori autor2 = TestDataUtil.createTestAutorC();
        underTest.save(autor2);

          Iterable <Autori> result = underTest.godineManjeOd(18);
          assertThat(result).containsExactly(autor2,autor1);


    }


    @Test
    public void TestKojiDobijaAutreVeceOd()
    {

        Autori autor = TestDataUtil.createTestAutor();
        underTest.save(autor);
        Autori autor1 = TestDataUtil.createTestAutorB();
        underTest.save(autor1);
        Autori autor2 = TestDataUtil.createTestAutorC();
        underTest.save(autor2);


         Iterable <Autori> result = underTest.nadjiAutoreVeceOd(15);
         assertThat(result).containsExactly(autor);

    }





}
