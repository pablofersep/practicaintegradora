package com.pablofersep.practicaintegradora.validaciones;

import com.pablofersep.practicaintegradora.repositorios.datos.TipoDocumentoClienteRepository;
import com.pablofersep.practicaintegradora.repositorios.datos.TipoViaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class TipoViaExistenteValidator implements ConstraintValidator<TipoViaExistente, String> {

    @Autowired
    private TipoViaRepository tipoViaRepository;

    @Override
    public boolean isValid(String g, ConstraintValidatorContext context) {
        if (tipoViaRepository.findTipoViasBySiglasEquals(g) != null){
            return true;
        }
        return false;
    }
}
