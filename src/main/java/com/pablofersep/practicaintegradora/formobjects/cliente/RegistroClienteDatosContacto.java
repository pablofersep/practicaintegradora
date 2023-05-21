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

    @NotNull(message = "´{error.notnull}")
    @NotBlank(message = "{error.notblank}")
    @Pattern(regexp = "[0-9]{9}", message = "{error.movil}")
    private String telefonoMovil;
    @NotNull(message = "{error.notnull}")
    @TipoDocumentoClienteExistente(message = "{error.tipodocumentoclienteexistente}")
    private String tipoDocumento;
    @NotNull(message = "{error.notnull}")
    @NotBlank(message = "{error.notblank}")
    private String documento;
    @NotNull(message = "{error.notnull}")
    @NotBlank(message = "{error.notblank}")
    private String nombreDireccion;
    @NotNull(message = "{error.notnull}")
    @TipoViaExistente(message = "{error.tipoviaexistente}")
    private String tipoVia;
    @NotNull(message = "{error.notnull}")
    @NotBlank(message = "{error.notblank}")
    @Pattern(regexp = "[0-9]{1,}", message = "{error.numero}")
    private String numero;
    private String portal;
    @Pattern(regexp = "[0-9]{1,}",message = "{error.numero}")
    private String planta;
    private String puerta;
    @NotNull(message = "{error.notnull}")
    @NotBlank(message = "{error.notblank}")
    private String localidad;
    private String region;
    @NotNull(message = "{error.notnull}")
    @NotBlank(message = "{error.notblank}")
    @Pattern(regexp = "[0-9]{1,}",message = "{error.numero}")
    private String codigoPostal;

}
