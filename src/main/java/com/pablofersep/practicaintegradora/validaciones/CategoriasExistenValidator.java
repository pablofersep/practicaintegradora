package com.pablofersep.practicaintegradora.validaciones;

import com.pablofersep.practicaintegradora.repositorios.datos.TipoViaRepository;
import com.pablofersep.practicaintegradora.servicios.principales.CategoriaService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoriasExistenValidator implements ConstraintValidator<CategoriasExisten, List<String>> {

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public boolean isValid(List<String> c, ConstraintValidatorContext context) {
        return categoriaService.existenCategorias(c);
    }
}
