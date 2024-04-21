package DAO.impl;

import DAO.KnjiaDAO;
import doomen.Knjige;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class KnjigaDAOImpl implements KnjiaDAO {


    private final JdbcTemplate jdbcTemplate;

    private  KnjigaDAOImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate= jdbcTemplate;
    }

    public void create(Knjige knjiga) {
        jdbcTemplate.update("INSERT INTO knjige (isbn,naslov,autor_id) VALUES (?,?,?)",
                knjiga.getIsbn(),knjiga.getNaslov(), knjiga.getAutor_id());
    }

    @Override
    public Optional<Knjige> find(String isbn) {
      List<Knjige> results =   jdbcTemplate.query("SELECT isbn,naziv, autor_id WHERE isbn =? LIMIT 1",
          new KnigreRawMapper(),isbn);


      return results.stream().findFirst();
    }










    public static class KnigreRawMapper implements RowMapper {


        @Override
        public Knjige mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Knjige.builder()
                    .isbn(rs.getString("isbn"))
                    .naslov(rs.getString("naslov"))
                    .autor_id(rs.getLong("autor_id"))
                    .build();

        }
    }

}
