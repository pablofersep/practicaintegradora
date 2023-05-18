package com.pablofersep.practicaintegradora.entidades.datos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TipoCliente {

    @Id
    private String siglas;
    @Column
    private String nombre;
    @Column
    private BigDecimal gastoUmbral;
    @Column
    private BigDecimal porcentajeDescuento;

}
