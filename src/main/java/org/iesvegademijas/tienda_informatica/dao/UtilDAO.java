package org.iesvegademijas.tienda_informatica.dao;
import org.iesvegademijas.tienda_informatica.modelo.Pedido;
import org.iesvegademijas.tienda_informatica.modelo.Cliente;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilDAO {

    public static Pedido newPedido(ResultSet rs) throws SQLException {
        return new Pedido(rs.getInt("id"),
                rs.getDouble("total"),
                rs.getDate("fecha"),
                new Cliente(rs.getInt("C.id"),
                        rs.getString("C.nombre"),
                        rs.getString("C.apellido1"),
                        rs.getString("C.apellido2"),
                        rs.getString("C.ciudad"),
                        rs.getInt("C.categoría")
                ),
                new Comercial(rs.getInt("CO.id"),
                        rs.getString("CO.nombre"),
                        rs.getString("CO.apellido1"),
                        rs.getString("CO.apellido2"),
                        rs.getFloat("CO.comision")
                )
        );
    }
    public static Cliente newCliente(ResultSet rs) throws SQLException {
        return new Cliente(rs.getInt("id")
                , rs.getString("nombre")
                , rs.getString("apellido1")
                , rs.getString("apellido2")
                , rs.getString("ciudad")
                , rs.getInt("categoría"),
                new Pedido(rs.getInt("p.id"),
                        rs.getDouble("p.total"),
                        rs.getDate("p.fecha"),
                        rs.getInt("p.id_cliente"),
                        rs.getInt("p.id_comercial")

                ),
                new Comercial(rs.getInt("CO.id"),
                        rs.getString("CO.nombre"),
                        rs.getString("CO.apellido1"),
                        rs.getString("CO.apellido2"),
                        rs.getFloat("CO.comision")
                )
        );
    }

    public static Comercial newComercial(ResultSet rs) throws SQLException {
        return new Comercial(rs.getInt("id")
                , rs.getString("nombre")
                , rs.getString("apellido1")
                , rs.getString("apellido2")
                , rs.getFloat("comisión"));
    }
}
