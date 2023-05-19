package com.pablofersep.practicaintegradora.formobjects.cliente;

import com.pablofersep.practicaintegradora.validaciones.TipoDocumentoClienteExistente;
import com.pablofersep.practicaintegradora.validaciones.TipoViaExistente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistroClienteDatosContacto {

    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]{9}")
    private String telefonoMovil;
    @NotNull
    @TipoDocumentoClienteExistente
    private String tipoDocumento;
    @NotNull
    @NotBlank
    private String documento;
    @NotNull
    @NotBlank
    private String nombreDireccion;
    @NotNull
    @TipoViaExistente
    private String tipoVia;
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]{1,}")
    private String numero;
    private String portal;
    @Pattern(regexp = "[0-9]{1,}")
    private String planta;
    private String puerta;
    @NotNull
    @NotBlank
    private String localidad;
    private String region;
    @NotNull
    @NotBlank
    @Pattern(regexp = "[0-9]{1,}")
    private String codigoPostal;

}
