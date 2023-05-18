package com.pablofersep.practicaintegradora.validaciones;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComprobarFechaNacimientoValidator implements ConstraintValidator<ComprobarFechaNacimiento, LocalDate> {

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        if (fechaNacimiento == null) return false;
        LocalDate ld = LocalDate.now();
        if (((ld.getYear() - fechaNacimiento.getYear()) > 18)
        ) {
            return true;
        }
        if (((ld.getYear() - fechaNacimiento.getYear()) == 18) &
                (ld.getMonthValue() > fechaNacimiento.getMonthValue())
        ) {
            return true;
        }
        if (((ld.getYear() - fechaNacimiento.getYear()) == 18) &
                (ld.getMonthValue() == fechaNacimiento.getMonthValue()) &
                (ld.getDayOfMonth() >= fechaNacimiento.getDayOfMonth())
        ) {
            return true;
        }
        return false;
    }

}