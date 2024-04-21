package DAO.impl;

import DAO.KnjiaDAO;
import doomen.Autori;
import org.springframework.jdbc.core.JdbcTemplate;

public class AutorDAOImpl implements KnjiaDAO {



    private final JdbcTemplate jdbcTemplate;


    public AutorDAOImpl(final JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void create(Autori autori) {
        jdbcTemplate.update("INSERT INTO autori (id,ime,godine) VALUES (?,?,?)",
                autori.getId(),autori.getIme(),autori.getGodine());
    }
}
