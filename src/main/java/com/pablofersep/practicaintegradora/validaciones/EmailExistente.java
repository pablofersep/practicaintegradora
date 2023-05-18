package com.pablofersep.practicaintegradora.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( {ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailExistenteValidator.class)

public @interface EmailExistente {

    public String message() default "El usuario ya existe en la base de datos";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
