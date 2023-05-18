package com.pablofersep.practicaintegradora.entidades.auxiliares;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class TarjetaCredito {

    @Column(name = "tipo_tarjeta_credito")
    private String tipoTarjetaCredito;
    @Column
    private String numero;
    @Column
    private String cvv;
    @Column(name = "fecha_caducidad")
    private LocalDate fechaCaducidad;

}
