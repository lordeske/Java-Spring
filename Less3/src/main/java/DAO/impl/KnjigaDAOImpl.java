package DAO.impl;

import DAO.KnjiaDAO;
import doomen.Knjige;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private  KnjigaDAOImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate= jdbcTemplate;
    }

    public void create(Knjige knjiga) {
        jdbcTemplate.update("INSERT INTO knjige (isbn,naslov,autor_id) VALUES (?,?,?)",
                knjiga.getIsbn(),knjiga.getNaslov(), knjiga.getAutor_id());
    }

    @Override
    public Optional<Knjige> findOne(String isbn) {
      List<Knjige> results =   jdbcTemplate.query("SELECT isbn,naziv, autor_id WHERE isbn =? LIMIT 1",
          new KnigreRawMapper(),isbn);


      return results.stream().findFirst();
    }

    @Override
    public List<Knjige> find() {
       return jdbcTemplate.query("SELECT isbn,naziv, autor_id from Knjige",
               new KnigreRawMapper());
    }

    @Override
    public void update(String l, Knjige knjige) {

        jdbcTemplate.update("UPDATE knjige SET isbn= ?, naziv = ?, autor_id = ? WHERE isbn = ?",
               knjige.getIsbn(),knjige.getNaslov(),knjige.getAutor_id(),l );

    }

    @Override
    public void delete(String s) {

        jdbcTemplate.update("DELETE FROM knjige WHERE isbn = ?",
                s);

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
