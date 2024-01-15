package org.iesvegademijas.tienda_informatica.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ComercialDAOImpl  implements ComercialDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial comercial) {
        String sqlInsert = """
							INSERT INTO comercial (nombre, apellido1, apellido2, comision) 
							VALUES  (     ?,         ?,         ?,       ?)
						   """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //Con recuperación de id generado
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
            int idx = 1;
            ps.setString(idx++, comercial.getNombre());
            ps.setString(idx++, comercial.getApellido1());
            ps.setString(idx++, comercial.getApellido2());
            ps.setFloat(idx++, comercial.getComision());
            return ps;
        },keyHolder);

        comercial.setId(keyHolder.getKey().intValue());

        //Sin recuperación de id generado
//		int rows = jdbcTemplate.update(sqlInsert,
//							cliente.getNombre(),
//							cliente.getApellido1(),
//							cliente.getApellido2(),
//							cliente.getCiudad(),
//							cliente.getCategoria()
//					);

        log.info("Insertados {} registros.", rows);
    }

    @Override
    public List<Comercial> getAll(){
    List<Comercial> listCom= jdbcTemplate.query(
            "SELECT * FROM comercial",
            (rs, rowNum) -> new Comercial(
                    rs.getString("nombre"),
                    rs.getString("apellido1"),
                    rs.getString("apellido2"),
                    rs.getFloat("comision")
            )
    );

		log.info("Devueltos {} registros.", listCom.size());

        return listCom;
    }

    @Override
    public Optional<Comercial> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Comercial comercial) {

    }

    @Override
    public void delete(int id) {

    }
}
