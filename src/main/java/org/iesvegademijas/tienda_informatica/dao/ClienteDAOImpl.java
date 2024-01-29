package org.iesvegademijas.tienda_informatica.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class ClienteDAOImpl implements  ClienteDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(Cliente cliente) {
        String sqlInsert = """
							INSERT INTO cliente (nombre, apellido1, apellido2, ciudad,categoría) 
							VALUES  (     ?,         ?,         ?,       ?,     ?)
						   """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //Con recuperación de id generado
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx++,cliente.getCategoría());
            return ps;
        },keyHolder);

        cliente.setId(keyHolder.getKey().intValue());

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
    public List<Cliente> getAll() {
        List<Cliente> listCom= jdbcTemplate.query(
                "SELECT * FROM cliente ",
                (rs, rowNum) -> new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoría")
                )
        );

        log.info("Devueltos {} registros.", listCom.size());

        return listCom;
    }
    @Override
    public List<Comercial> getAllByCliente(int id) {
        String sql = """
            SELECT c.*
            FROM cliente cl
            left JOIN pedido p ON cl.id = p.id_cliente
            left JOIN comercial c ON p.id_comercial = c.id
            WHERE cl.id = ?    
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Comercial comercial = new Comercial();
            comercial.setId(rs.getInt("id"));
            comercial.setNombre(rs.getString("nombre"));
            comercial.setApellido1(rs.getString("apellido1"));
            comercial.setApellido2(rs.getString("apellido2"));
            comercial.setComision(rs.getFloat("comision"));
            return comercial;
        }, id);

    }

    @Override
    public Optional<Cliente> find(int id) {
        Cliente cli =  jdbcTemplate
                .queryForObject("SELECT * FROM cliente WHERE id = ?"
                        , (rs, rowNum) -> new Cliente(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido1"),rs.getString("apellido2"),rs.getString("ciudad"),rs.getInt("categoría"))
                        , id
                );

        if (cli != null) return Optional.of(cli);
        else return Optional.empty();
    }

    @Override
    public void update(Cliente cliente) {
        int rows = jdbcTemplate.update("UPDATE cliente SET nombre = ?  WHERE id = ?", cliente.getNombre(), cliente.getId());
        if (rows == 0) System.out.println("Update de cliente con 0 registros actualizados.");

    }

    @Override
    public void delete(int id) {
        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        if (rows == 0) System.out.println("Update de cliente con 0 registros actualizados.");

    }
    @Override
    public int calcularConteoPedidosUltimoTrimestre(Cliente cliente) {
        // Obtener la fecha de hace tres meses
        LocalDate fechaInicio = LocalDate.now().minusMonths(3);

        // Consulta SQL para contar los pedidos del último trimestre para el cliente dado
        String sql = "SELECT COUNT(*) FROM pedido WHERE id_cliente = ? AND fecha >= ?";

        // Ejecutar la consulta
        return jdbcTemplate.queryForObject(sql, Integer.class, cliente.getId(), fechaInicio);
    }

    @Override
    public int calcularConteoPedidosUltimoSemestre(Cliente cliente) {
        // Obtener la fecha de hace seis meses
        LocalDate fechaInicio = LocalDate.now().minusMonths(6);

        // Consulta SQL para contar los pedidos del último semestre para el cliente dado
        String sql = "SELECT COUNT(*) FROM pedido WHERE id_cliente = ? AND fecha >= ?";

        // Ejecutar la consulta
        return jdbcTemplate.queryForObject(sql, Integer.class, cliente.getId(), fechaInicio);
    }

    @Override
    public int calcularConteoPedidosUltimoAnio(Cliente cliente) {
        // Obtener la fecha de hace un año
        LocalDate fechaInicio = LocalDate.now().minusYears(1);

        // Consulta SQL para contar los pedidos del último año para el cliente dado
        String sql = "SELECT COUNT(*) FROM pedido WHERE id_cliente = ? AND fecha >= ?";

        // Ejecutar la consulta
        return jdbcTemplate.queryForObject(sql, Integer.class, cliente.getId(), fechaInicio);
    }

    @Override
    public int calcularConteoPedidosUltimoLustro(Cliente cliente) {
        // Obtener la fecha de hace cinco años
        LocalDate fechaInicio = LocalDate.now().minusYears(5);

        // Consulta SQL para contar los pedidos del último lustro para el cliente dado
        String sql = "SELECT COUNT(*) FROM pedido WHERE id_cliente = ? AND fecha >= ?";

        // Ejecutar la consulta
        return jdbcTemplate.queryForObject(sql, Integer.class, cliente.getId(), fechaInicio);
    }
}
