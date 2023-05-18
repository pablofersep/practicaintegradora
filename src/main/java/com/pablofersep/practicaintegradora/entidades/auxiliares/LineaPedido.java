package com.pablofersep.practicaintegradora.entidades.auxiliares;

import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class LineaPedido {

    @ManyToOne
    private Producto producto;
    @Column
    private Integer unidades;
    @Column(name = "precio_unitario", precision=7, scale=2)
    private BigDecimal precioUnitario;
}
