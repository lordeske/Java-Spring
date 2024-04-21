package DAO.impl;

import DAO.KnjiaDAO;
import org.springframework.jdbc.core.JdbcTemplate;

public class KnjigaDAOImpl implements KnjiaDAO {


    private final JdbcTemplate jdbcTemplate;

    private  KnjigaDAOImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate= jdbcTemplate;
    }

}
