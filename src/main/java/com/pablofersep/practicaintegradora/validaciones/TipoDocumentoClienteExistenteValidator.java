package com.pablofersep.practicaintegradora.validaciones;

import com.pablofersep.practicaintegradora.repositorios.datos.TipoDocumentoClienteRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class TipoDocumentoClienteExistenteValidator implements ConstraintValidator<TipoDocumentoClienteExistente, String> {

    @Autowired
    private TipoDocumentoClienteRepository tipoDocumentoClienteRepository;

    @Override
    public boolean isValid(String g, ConstraintValidatorContext context) {
        if (tipoDocumentoClienteRepository.findTipoDocumentoClienteBySiglasEquals(g) != null){
            return true;
        }
        return false;
    }
}
