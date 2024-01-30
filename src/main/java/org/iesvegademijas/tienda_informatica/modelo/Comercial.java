package org.iesvegademijas.tienda_informatica.modelo;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/*nombre - obligatorio, con una longitud máxima de 30
apellido1- obligatorio, con una longitud máxima de 30
apellido2 - opcional*/
@Data
@AllArgsConstructor
public class Comercial {
    private int id;
    @NotNull
    @Length(min=1,max=30,message = "Demasiado grande o pequeño maximo 30 letras")
    private String nombre;
    @NotNull
    @Length(min=1,max=30,message = "Demasiado grande o pequeño maximo 30 letras")
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
