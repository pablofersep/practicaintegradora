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

    @NotNull(message = "{error.notnull}")
    @NotBlank(message = "{error.notblank}")
    private String nombre;
    @NotNull(message = "{error.notnull}")
    @NotBlank(message = "{error.notblank}")
    private String apellidos;
    @NotNull(message = "{error.notnull}")
    @ComprobarFechaNacimiento(message = "{error.comprobarfechanacimiento}")
    private LocalDate fechaNacimiento;
    @NotNull(message = "{error.notnull}")
    @PaisExistente(message = "{error.paisexistente}")
    private String paisSeleccionado;
    @NotNull(message = "{error.notnull}")
    @GeneroExistente(message = "{error.generoexistente}")
    private String generoSeleccionado;

}
