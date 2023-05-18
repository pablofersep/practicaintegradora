package com.pablofersep.practicaintegradora.validaciones;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.PARAMETER, ElementType.TYPE} )
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ClavesIgualesValidator.class)

public @interface ClavesIguales {

    public String message() default "Las claves tienen que ser iguales";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
