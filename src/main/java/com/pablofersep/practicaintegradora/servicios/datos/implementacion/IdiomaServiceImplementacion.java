package com.pablofersep.practicaintegradora.servicios.datos.implementacion;

import com.pablofersep.practicaintegradora.entidades.datos.Idioma;
import com.pablofersep.practicaintegradora.repositorios.datos.IdiomaRepository;
import com.pablofersep.practicaintegradora.servicios.datos.IdiomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IdiomaServiceImplementacion implements IdiomaService {
    @Autowired
    private IdiomaRepository idiomaRepository;

    @Override
    public Idioma findBySiglas(String siglas) {
        return idiomaRepository.findIdiomaBySiglasEquals(siglas);
    }

    @Override
    public List<Idioma> findAll() {
        return idiomaRepository.findAll();
    }
}
