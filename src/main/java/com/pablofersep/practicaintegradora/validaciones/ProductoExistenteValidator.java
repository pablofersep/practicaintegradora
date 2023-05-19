package com.pablofersep.practicaintegradora.validaciones;

import com.pablofersep.practicaintegradora.repositorios.principales.UsuarioRepository;
import com.pablofersep.practicaintegradora.servicios.principales.ProductoService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductoExistenteValidator implements ConstraintValidator<ProductoExistente, String> {

    @Autowired
    private ProductoService productoService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if (productoService.findProductoByCodigo(s) != null){
            return false;
        }
        return true;
    }
}
