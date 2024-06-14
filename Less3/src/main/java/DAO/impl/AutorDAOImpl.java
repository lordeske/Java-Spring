package DAO.impl;

import DAO.AutorDAO;
import doomen.Autori;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


    @Component
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

    @Override
    public List<Autori> find() {
        return jdbcTemplate.query("SELECT id, ime, godine FROM autori",
                new AutorRowMapper());
    }

    @Override
    public void update(long id, Autori autori) {

        jdbcTemplate.update("UPDATE autori SET id = ? , ime = ?, godine = ? WHERE id= ?",
                autori.getId(),autori.getIme(),autori.getGodine(),id);

    }

    @Override
    public void delete(long l) {

        jdbcTemplate.update("DELETE FROM autori WHERE id = ?",
                l);

    }


        public static class AutorRowMapper implements RowMapper<Autori> {
        @Override public Autori mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Autori.builder()
                    .id(rs.getLong("id"))
                    .ime(rs.getString("ime"))
                    .godine(rs.getInt("godine"))
                    .build();
        }
    }


}
