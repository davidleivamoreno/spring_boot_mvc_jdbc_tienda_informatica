package org.iesvegademijas.tienda_informatica.dao;

import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PedidoDAOImpl implements PedidoDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Pedido pedido) {
        String sqlInsert = """
							INSERT INTO pedido (total, fecha, id_cliente, id_comercial) 
							VALUES  (     ?,         ?,         ?,       ?)
						   """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //Con recuperación de id generado
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
            int idx = 1;
            ps.setDouble(idx++, pedido.getTotal());
            ps.setDate(idx++, pedido.getFecha());
            return ps;
        },keyHolder);

        pedido.setId(keyHolder.getKey().intValue());

        //Sin recuperación de id generado
//		int rows = jdbcTemplate.update(sqlInsert,
//							cliente.getNombre(),
//							cliente.getApellido1(),
//							cliente.getApellido2(),
//							cliente.getCiudad(),
//							cliente.getCategoria()
//					);



    }

    public List<Pedido> getAll() {
      List<Pedido> pedidos=jdbcTemplate.query("""
            SELECT * FROM pedido P
            LEFT JOIN cliente C ON P.id_cliente = C.id
            LEFT JOIN comercial CO ON P.id_comercial = CO.id
            """, (rs, rowNum) -> UtilDAO.newPedido(rs));
        Pedido pedidoConMayorCosto=pedidos.stream()
                .max(Comparator.comparing(Pedido::getTotal))
                .orElse(null);
        if (pedidoConMayorCosto != null) {
            pedidos.forEach(pedido -> {
                if (pedido.equals(pedidoConMayorCosto)) {
                    pedido.setColoreado(true);  // Supongamos que hay un método setColoreado en la clase Pedido
                }
            });
        }
        return pedidos;

    }
    @Override
    public List<Pedido> getAllByComercial(int id) {
        List<Pedido> pedidos=jdbcTemplate.query("""
            SELECT * FROM pedido P
            LEFT JOIN cliente C ON P.id_cliente = C.id
            LEFT JOIN comercial CO ON P.id_comercial = CO.id 
            where p.id_comercial =?;
            """, (rs, rowNum) -> UtilDAO.newPedido(rs), id);

        //Saco el pedido con mayor costo de toda la lista e intento colorearlo
        Pedido pedidoConMayorCosto=pedidos.stream()
                .max(Comparator.comparing(Pedido::getTotal))
                .orElse(null);
        //Si existe
        if (pedidoConMayorCosto != null) {
            pedidos.forEach(pedido -> {
                if (pedido.getId()==(pedidoConMayorCosto.getId())) {
                    pedido.setColoreado(true);  // Supongamos que hay un método setColoreado en la clase Pedido
                }
            });
        }


        return pedidos;

    }
    @Override
    public List<Cliente> getClientesOrdenadosPorCuantiaTotal(int idComercial) {
        // Obtener todos los pedidos para un comercial específico
        List<Pedido> pedidos = jdbcTemplate.query("""
            SELECT * FROM pedido P
            LEFT JOIN cliente C ON P.id_cliente = C.id
            LEFT JOIN comercial CO ON P.id_comercial = CO.id
            WHERE P.id_comercial = ?
        """, (rs, rowNum) -> UtilDAO.newPedido(rs), idComercial);

        // Agrupar los pedidos por cliente y calcular la cuantía total de cada cliente
        Map<Cliente, Double> cuantiaPorCliente = pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente, Collectors.summingDouble(Pedido::getTotal)));

        // Ordenar los clientes por cuantía total de mayor a menor
        List<Cliente> clientesOrdenados = cuantiaPorCliente.entrySet().stream()
                .sorted(Map.Entry.<Cliente, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return clientesOrdenados;
    }


    @Override
    public double getMedia(List<Pedido> pedidos) {
        return pedidos.stream()
                .mapToDouble(Pedido::getTotal)
                .average()
                .orElse(0);
    }
    @Override
    public double getSumatorio(List<Pedido> pedidos) {
        return pedidos.stream()
                .mapToDouble(Pedido::getTotal)
                .sum();
    }


    @Override
    public Optional<Pedido> find(int id) {
        Pedido pedido= this.jdbcTemplate.queryForObject("""
                    select * from pedido P left join cliente C on  P.id_cliente = C.id
                                        left join comercial CO on P.id_comercial = CO.id
                                        WHERE P.id = ?
                """, (rs, rowNum) -> UtilDAO.newPedido(rs), id);

        if (pedido != null) return Optional.of(pedido);
        return Optional.empty();
    }

    @Override
    public void update(Pedido pedido) {
        int rows = jdbcTemplate.update("UPDATE pedido SET total = ?,fecha=?  WHERE id = ?", pedido.getTotal(),pedido.getFecha(), pedido.getId());
        if (rows == 0) System.out.println("Update de comercial con 0 registros actualizados.");

    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de cliente con 0 registros actualizados.");

    }

}
