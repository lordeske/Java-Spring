package com.bazapodatak1.database1.dao;

import DAO.impl.AutorDAOImpl;
import doomen.Autori;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD )
public class AutorDAOImplIntegrationTest {



    private final AutorDAOImpl underTest;

    @Autowired
    public AutorDAOImplIntegrationTest(AutorDAOImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAutorIsCreated() {
        Autori autor = TestDataUtil.createTestAutor();
        underTest.create(autor);

        Optional<Autori> result = underTest.findOne(autor.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(autor);
    }

    @Test
    public void testThatMultyAutorsCanBeCreatedAndCalled()
    {
        Autori autoriA = TestDataUtil.createTestAutorA() ;
        underTest.create(autoriA);
        Autori autoriB = TestDataUtil.createTestAutorB() ;
        underTest.create(autoriB);
        Autori autoriC = TestDataUtil.createTestAutorC() ;
        underTest.create(autoriC);


        List<Autori> autoriLista = underTest.find();
        assertThat(autoriLista).hasSize
                (3).contains(autoriA,autoriB,autoriC);

    }

    @Test
    public void testThatAutorIsUp()
    {
        Autori autoriA = TestDataUtil.createTestAutorA() ;
        underTest.create(autoriA);

        autoriA.setIme("Milovan");
        underTest.update(autoriA.getId(),autoriA);

        Optional <Autori> result  = underTest.findOne(autoriA.getId());

        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(autoriA);



    }




}
