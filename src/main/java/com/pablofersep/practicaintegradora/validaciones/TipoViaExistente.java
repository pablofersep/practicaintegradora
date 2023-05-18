package com.pablofersep.practicaintegradora.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( {ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TipoViaExistenteValidator.class)

public @interface TipoViaExistente {

    public String message() default "El tipo de via no se encuentra en la BBDD";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
