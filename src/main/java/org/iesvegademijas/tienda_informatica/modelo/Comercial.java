package org.iesvegademijas.tienda_informatica.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comercial {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float comision;
    public int conteoPedidosUltimoTrimestre;
    public int conteoPedidosUltimoSemestre;
    public int conteoPedidosUltimoAnio;
    public int conteoPedidosUltimoLustro;
    public Comercial(){

    }


    public Comercial(int id, String nombre, String apellido1, String apellido2, float comision) {
        this.id=id;
        this.nombre=nombre;
        this.apellido1=apellido1;
        this.apellido2=apellido2;
        this.comision=comision;
    }
}
