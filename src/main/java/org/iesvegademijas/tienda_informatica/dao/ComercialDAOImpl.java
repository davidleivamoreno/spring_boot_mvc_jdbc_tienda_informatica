package org.iesvegademijas.tienda_informatica.dao;

import java.util.List;
import java.util.Optional;

import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ComercialDAOImpl  implements ComercialDAO{


    @Override
    public void create(Comercial comercial) {

    }

    @Override
    public List<Comercial> getAll() {
        return null;
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
