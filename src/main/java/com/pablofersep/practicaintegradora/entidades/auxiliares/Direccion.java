package com.pablofersep.practicaintegradora.entidades.auxiliares;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Direccion {

    @Column(name = "nombre_direccion")
    private String nombre;
    @Column(name = "tipo_via")
    private String tipoVia;
    @Column
    private Integer numero;
    @Column
    private String portal;
    @Column
    private String planta;
    @Column
    private String puerta;
    @Column
    private String localidad;
    @Column
    private String region;
    @Column
    private String codigoPostal;

}
