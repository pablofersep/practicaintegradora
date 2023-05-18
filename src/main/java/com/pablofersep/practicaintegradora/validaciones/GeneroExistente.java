package com.pablofersep.practicaintegradora.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( {ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = GeneroExistenteValidator.class)

public @interface GeneroExistente {

    public String message() default "El genero no Existe en la BBDD";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
