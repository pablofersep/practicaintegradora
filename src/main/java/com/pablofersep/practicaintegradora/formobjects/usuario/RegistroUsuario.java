package com.pablofersep.practicaintegradora.formobjects.usuario;

import com.pablofersep.practicaintegradora.validaciones.ClavesIguales;
import com.pablofersep.practicaintegradora.validaciones.EmailExistente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ClavesIguales
public class RegistroUsuario {

    @NotNull(message="No puede ser nulo")
    @NotBlank(message="No puede estar en blanco")
    @Pattern(regexp="^(.+)@(.+)\\.(.+)$", message= "El email no es valido")
    @EmailExistente
    private String email;
    @NotNull(message="No puede ser nulo")
    @NotBlank(message="No puede estar en blanco")
    private String clave;
    @NotNull(message="No puede ser nulo")
    @NotBlank(message="No puede estar en blanco")
    private String confirmarClave;

}
