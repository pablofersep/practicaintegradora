package com.pablofersep.practicaintegradora.entidades.datos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ClaseProveedor {

    @Id
    private String siglas;
    @Column
    private String nombre;
}
