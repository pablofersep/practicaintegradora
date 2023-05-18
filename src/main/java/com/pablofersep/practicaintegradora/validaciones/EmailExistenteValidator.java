package com.pablofersep.practicaintegradora.validaciones;

import com.pablofersep.practicaintegradora.repositorios.principales.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailExistenteValidator implements ConstraintValidator<EmailExistente, String> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (usuarioRepository.findByEmailEquals(email) != null){
            return false;
        }
        return true;
    }
}
