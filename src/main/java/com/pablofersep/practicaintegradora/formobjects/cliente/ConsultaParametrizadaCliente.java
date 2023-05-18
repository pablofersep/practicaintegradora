package com.pablofersep.practicaintegradora.formobjects.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaParametrizadaCliente {

    @NotNull
    private String apellidoPattern;
    @NotBlank
    @NotNull
    private String tipoCliente;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private BigDecimal gastoIni;
    private BigDecimal gastoFin;

}
