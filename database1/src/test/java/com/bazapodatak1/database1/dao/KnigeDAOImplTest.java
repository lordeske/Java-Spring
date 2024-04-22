package com.bazapodatak1.database1.dao;

import DAO.impl.KnjigaDAOImpl;
import doomen.Autori;
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

        testObject.findOne("1-123-456-7");

        verify(jdbcTemplate).query(eq("SELECT isbn,naziv, autor_id WHERE isbn =? LIMIT 1"),
                ArgumentMatchers.<KnjigaDAOImpl.KnigreRawMapper>any(),
                eq("1-123-456-7"));





    }

    @Test
    public void testThatFindAllSQL()
    {
        testObject.find();

        verify(jdbcTemplate).query(eq("SELECT isbn,naziv, autor_id from Knjige"),
                ArgumentMatchers.<KnjigaDAOImpl.KnigreRawMapper>any());



    }


    @Test
    public void testThatUpdateBook()
    {

        Autori autori = TestDataUtil.createTestAutor();
        Knjige knjige = TestDataUtil.createTestKnjiga();
        knjige.setAutor_id(autori.getId());
        testObject.update("1-123-456-7",knjige);
        verify(jdbcTemplate).update("UPDATE knjige SET isbn= ?, naziv = ?, autor_id = ? WHERE isbn = ?",
                "1-123-456-7","Mracna Carobnica",1L,"1-123-456-7");


    }



    @Test
    public void testKnjigaDelete()
    {

        testObject.delete("1-123-456-7");

        verify(jdbcTemplate).update("DELETE FROM knjige WHERE isbn = ?",
                "1-123-456-7");


    }


}
