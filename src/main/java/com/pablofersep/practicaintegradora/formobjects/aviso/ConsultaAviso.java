package com.pablofersep.practicaintegradora.formobjects.aviso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaAviso {

    private String urgencia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
