package com.pablofersep.practicaintegradora.validaciones;

import com.pablofersep.practicaintegradora.repositorios.datos.GeneroRepository;
import com.pablofersep.practicaintegradora.repositorios.principales.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneroExistenteValidator implements ConstraintValidator<GeneroExistente, String> {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public boolean isValid(String g, ConstraintValidatorContext context) {
        if (generoRepository.findGeneroBySiglasEquals(g) != null){
            return true;
        }
        return false;
    }
}
