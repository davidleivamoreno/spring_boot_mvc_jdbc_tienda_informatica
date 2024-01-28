package org.iesvegademijas.tienda_informatica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesvegademijas.tienda_informatica.modelo.Comercial;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private int categoria;
    private List<Comercial> comerciales;
    private int conteoPedidosUltimoTrimestre;
    private int conteoPedidosUltimoSemestre;
    private int conteoPedidosUltimoAnio;
    private int conteoPedidosUltimoLustro;
}
