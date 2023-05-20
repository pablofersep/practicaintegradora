package com.pablofersep.practicaintegradora.servicios.datos.implementacion;

import com.pablofersep.practicaintegradora.entidades.datos.Genero;
import com.pablofersep.practicaintegradora.repositorios.datos.GeneroRepository;
import com.pablofersep.practicaintegradora.servicios.datos.GeneroService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GeneroServiceImplementacion implements GeneroService {
    @Autowired
    private GeneroRepository generoRepository;
    @Override
    public Genero findBySiglas(String siglas) {
        return generoRepository.findGeneroBySiglasEquals(siglas);
    }

    @Override
    public List<Genero> findAll() {
        return generoRepository.findAll();
    }
}
