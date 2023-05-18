package com.pablofersep.practicaintegradora.entidades.auxiliares;

import com.pablofersep.practicaintegradora.entidades.principales.Producto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class LineaCarrito {

    @Column
    private Integer unidades;
    @ManyToOne
    private Producto producto;

}
