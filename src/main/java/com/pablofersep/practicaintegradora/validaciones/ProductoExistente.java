package com.pablofersep.practicaintegradora.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target( {ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProductoExistenteValidator.class)

public @interface ProductoExistente {

    public String message() default "El producto ya existe en la base de datos";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
