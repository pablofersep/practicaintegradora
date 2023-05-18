package com.pablofersep.practicaintegradora.entidades.auxiliares;

import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class LineaCatalogo {

    @ManyToOne
    private Producto producto;
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

}
