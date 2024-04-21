package DAO.impl;

import DAO.KnjiaDAO;
import org.springframework.jdbc.core.JdbcTemplate;

public class AutorDAOImpl implements KnjiaDAO {



    private final JdbcTemplate jdbcTemplate;


    public AutorDAOImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
