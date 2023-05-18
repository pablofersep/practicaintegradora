package com.pablofersep.practicaintegradora.entidades.datos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Pais {

    @Id
    private String siglas;
    @Column
    private String nombre;

}
