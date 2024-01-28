package org.iesvegademijas.tienda_informatica.dao;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {
    public void create(Pedido pedido);

    public List<Pedido> getAll();
    public List<Pedido> getAllByComercial(int id);
    public Optional<Pedido> find(int id);

    public void update(Pedido pedido);

    public void delete(int id);
    public double getMedia(List<Pedido> pedidos);
    public double getSumatorio(List<Pedido> pedidos);
    public List<Cliente>getClientesOrdenadosPorCuantiaTotal(int id);
}
