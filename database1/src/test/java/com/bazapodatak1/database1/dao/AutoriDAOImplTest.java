package com.bazapodatak1.database1.dao;

import DAO.impl.AutorDAOImpl;
import doomen.Autori;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AutoriDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AutorDAOImpl underTest;


    @Test
    public void testCreateAutorGenCorrSQL()
    {
        Autori autori = Autori.builder()
                .id(1l)
                    .ime("Mihajlo")
                        .godine(20).build();

        underTest.create(autori);

        verify(jdbcTemplate).update(eq("INSERT INTO autori (id,ime,godine) VALUES (?,?,?)"),
                eq(1l),eq("Mihajlo"),eq(20));
    }
}
