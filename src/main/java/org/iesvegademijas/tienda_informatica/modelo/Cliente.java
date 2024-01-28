package org.iesvegademijas.tienda_informatica.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Range;

import java.util.List;
/*
* nombre - obligatorio, con una longitud máxima de 30
apellido1- obligatorio, con una longitud máxima de 30
apellido2 - opcional
ciudad - obligatorio, con longitud máxima de 50
categoria - con un rango de valores de 100 a 1000 extremos incluidos*/
@Data
@AllArgsConstructor
public class Cliente {
    private int id;
    @NotNull(message = "No puede ser nulo")
    @NotBlank(message = "No has introducido nada en el campo nombre")
    @Max(value=30,message="Es demasiado largo el nombre máximo 30 caracteres")
    private String nombre;

    @NotNull(message = "No puede ser nulo")
    @NotBlank(message = "No has introducido nada en el campo apellido1")
    @Max(value=30,message="Es demasiado largo el nombre máximo 30 caracteres")
    private String apellido1;
    @NotNull(message = "No puede ser nulo")
    @NotBlank(message = "No has introducido nada en el campo ciudad")
    @Max(value=30,message="Es demasiado largo la ciudad máximo 30 caracteres")
    private String apellido2;
    @Max(value=30,message="Es demasiado largo la ciudad máximo 30 caracteres")
    @NotBlank(message = "No has introducido nada en el campo ciudad")
    @NotNull(message = "No puede ser nulo")
    private String ciudad;
    @DecimalMin(value="100.0", message = "{msg.valid.min}")
    @DecimalMax(value="1000.0", message = "{msg.valid.max}")
    private int categoría;
    private Comercial comercial;
    private Cliente cliente;
    private List<Comercial> comerciales;
    public Cliente(){

    }


    public Cliente(int id, String nombre, String apellido1, String apellido2, String ciudad, int categoría, Pedido pedido, Comercial comercial) {
    this.id=id;
    this.nombre=nombre;
    this.apellido1=apellido1;
    this.apellido2=apellido2;
    this.ciudad=ciudad;
    this.categoría=categoría;
    this.comercial=comercial;
    this.cliente=cliente;
    }


    public Cliente(int id, String nombre, String apellido1, String apellido2, String ciudad, int categoría) {
        this.id=id;
        this.nombre=nombre;
        this.apellido1=apellido1;
        this.apellido2=apellido2;
        this.ciudad=ciudad;
        this.categoría=categoría;
    }
}
