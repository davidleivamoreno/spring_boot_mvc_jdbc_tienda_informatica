package org.iesvegademijas.tienda_informatica.modelo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Pedido {
    @Min(value=1, message = "{msg.valid.min}")
    private int id;
    @NotNull(message = "{msg.valid.not.null}")
    @DecimalMin(value="0.0", message = "{msg.valid.min}")
    @DecimalMin(value="100.0", message = "{msg.valid.max}")
    private Double total;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    @Valid
    private Cliente cliente;
    @Valid
    private Comercial comercial;

    public Pedido() {

    }

    public Pedido(int anInt, double aDouble, Date date, int anInt1, int anInt2) {

    }

    public void setColoreado(boolean b) {
        this.coloreado=true;
    }
     boolean coloreado;


    public Pedido(int id, double total, Date fecha, Cliente cliente, Comercial comercial) {
        this.id=id;
        this.total=total;
        this.fecha=fecha;
        this.cliente=cliente;
        this.comercial=comercial;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(total, pedido.total);
    }
    public boolean isColoreado() {
        return this.coloreado;
    }


    @Override
    public int hashCode() {
        return Objects.hash(total);
    }
}
