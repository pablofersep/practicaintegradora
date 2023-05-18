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

    @CategoriasExisten
    private List<String> categoriasSeleccionadas;
    @NotNull
    private String comentarios;
    @NotNull
    @NotBlank
    @Pattern(regexp = "on")
    private String aceptaLicencia;

}
