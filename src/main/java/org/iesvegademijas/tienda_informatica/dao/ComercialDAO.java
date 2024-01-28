package org.iesvegademijas.tienda_informatica.dao;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Fabricante;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;

import java.util.List;
import java.util.Optional;

public interface ComercialDAO {
    public void create(Comercial comercial);

    public List<Comercial> getAll();
    public Optional<Comercial> find(int id);

    public void update(Comercial comercial);

    public void delete(int id);
    public List<Cliente> getClientesOrdenadosPorCuantiaTotal();
}
