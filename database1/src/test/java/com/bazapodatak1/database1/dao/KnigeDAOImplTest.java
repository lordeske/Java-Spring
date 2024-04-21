package com.bazapodatak1.database1.dao;

import DAO.impl.KnjigaDAOImpl;
import doomen.Knjige;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KnigeDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private KnjigaDAOImpl testObject;

    @Test
    public void testCreateBookGenCorrSQL()
    {

        Knjige knjiga = TestDataUtil.createTestKnjiga();


        testObject.create(knjiga);

        verify(jdbcTemplate).update(eq("INSERT INTO knjige (isbn,naslov,autor_id) VALUES (?,?,?)"),
                eq("1-123-456-7"),eq("Mracna Carobnica"),eq(1L));

    }


    @Test
    public void testFindSQL()
    {

        testObject.find("1-123-456-7");

        verify(jdbcTemplate).query(eq("SELECT isbn,naziv, autor_id WHERE isbn =? LIMIT 1"),
                ArgumentMatchers.<KnjigaDAOImpl.KnigreRawMapper>any(),
                eq("1-123-456-7"));





    }





}
