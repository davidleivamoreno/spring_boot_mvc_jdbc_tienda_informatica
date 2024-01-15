package org.iesvegademijas.tienda_informatica.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comercial {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float comision;
    public Comercial(){

    }

    public void setId(int i) {
    }
}
