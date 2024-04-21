package com.bazapodatak1.database1.dao;

import DAO.impl.AutorDAOImpl;
import doomen.Autori;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AutorDAOImplIntegrationTest {


    @Autowired
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
}
