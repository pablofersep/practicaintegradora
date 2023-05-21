package com.pablofersep.practicaintegradora.formobjects.cliente;

import com.pablofersep.practicaintegradora.entidades.principales.Categoria;
import com.pablofersep.practicaintegradora.validaciones.CategoriasExisten;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistroClienteDatosCliente {

    @CategoriasExisten(message = "{error.categoriasexisten}")
    private List<String> categoriasSeleccionadas;
    @NotNull(message = "{error.notnull}")
    private String comentarios;
    @NotNull(message = "{error.licencia}")
    @NotBlank(message = "{error.licencia}")
    @Pattern(regexp = "on", message = "{error.licencia}")
    private String aceptaLicencia;

}
