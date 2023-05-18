package com.pablofersep.practicaintegradora.formobjects.cliente;

import com.pablofersep.practicaintegradora.validaciones.ComprobarFechaNacimiento;
import com.pablofersep.practicaintegradora.validaciones.GeneroExistente;
import com.pablofersep.practicaintegradora.validaciones.PaisExistente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistroClienteDatosPersonales {

    @NotNull
    @NotBlank
    private String nombre;
    @NotNull
    @NotBlank
    private String apellidos;
    @NotNull
    @ComprobarFechaNacimiento
    private LocalDate fechaNacimiento;
    @NotNull
    @PaisExistente
    private String paisSeleccionado;
    @NotNull
    @GeneroExistente
    private String generoSeleccionado;

}
