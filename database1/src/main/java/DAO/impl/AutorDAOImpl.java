package DAO.impl;

import DAO.AutorDAO;
import DAO.KnjiaDAO;
import doomen.Autori;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AutorDAOImpl implements AutorDAO {



    private final JdbcTemplate jdbcTemplate;


    public AutorDAOImpl(final JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void create(Autori autori) {
        jdbcTemplate.update("INSERT INTO autori (id,ime,godine) VALUES (?,?,?)",
                autori.getId(),autori.getIme(),autori.getGodine());
    }

    @Override
    public Optional<Autori> findOne(long autorID) {
        List<Autori> results = jdbcTemplate.query("SELECT id,ime,godine FROM autori WHERE id = ? LIMIT 1",
                new AutorRowMapper(), autorID);

       return results.stream().findFirst();
    }


    public static class AutorRowMapper implements RowMapper<Autori> {

        @Override
        public Autori mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Autori.builder()
                    .id(rs.getLong("id"))
                    .ime(rs.getString("ime"))
                    .godine(rs.getInt("godine"))
                    .build();
        }
    }


}
