package com.pablofersep.practicaintegradora.validaciones;

import com.pablofersep.practicaintegradora.entidades.datos.Pais;
import com.pablofersep.practicaintegradora.repositorios.datos.GeneroRepository;
import com.pablofersep.practicaintegradora.repositorios.datos.PaisRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class PaisExistenteValidator implements ConstraintValidator<PaisExistente, String> {

    @Autowired
    private PaisRepository paisRepository;

    @Override
    public boolean isValid(String g, ConstraintValidatorContext context) {
        if (paisRepository.findPaisBySiglasEquals(g) != null){
            return true;
        }
        return false;
    }
}
