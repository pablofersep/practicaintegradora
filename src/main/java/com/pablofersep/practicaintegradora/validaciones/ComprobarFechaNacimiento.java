package com.pablofersep.practicaintegradora.validaciones;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ComprobarFechaNacimientoValidator.class)
public @interface ComprobarFechaNacimiento {
    
    public String message() default "errors.comprobarFechaNacimiento";
    
    public Class<?>[] groups() default {};
    
    public Class<? extends Payload>[] payload() default {};
}
