package com.pablofersep.practicaintegradora.validaciones;

import com.pablofersep.practicaintegradora.formobjects.usuario.RegistroUsuario;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClavesIgualesValidator implements ConstraintValidator<ClavesIguales, RegistroUsuario> {

    @Override
    public boolean isValid(RegistroUsuario dataForm, ConstraintValidatorContext context) {
        String clave = dataForm.getClave();
        String confirmarClave = dataForm.getConfirmarClave();
        if (clave != null & confirmarClave != null) {
            if (clave.equals(confirmarClave)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
