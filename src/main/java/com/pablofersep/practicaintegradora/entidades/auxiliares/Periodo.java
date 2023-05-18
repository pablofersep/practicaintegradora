package com.pablofersep.practicaintegradora.entidades.auxiliares;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Periodo {

    @Column
    private LocalDate fechaInicio;
    @Column
    private LocalDate fechaFinal;
}