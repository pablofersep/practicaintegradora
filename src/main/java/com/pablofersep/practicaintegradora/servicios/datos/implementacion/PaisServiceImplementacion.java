package com.pablofersep.practicaintegradora.servicios.datos.implementacion;

import com.pablofersep.practicaintegradora.entidades.datos.Pais;
import com.pablofersep.practicaintegradora.repositorios.datos.PaisRepository;
import com.pablofersep.practicaintegradora.servicios.datos.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaisServiceImplementacion implements PaisService {
    @Autowired
    private PaisRepository paisRepository;
    @Override
    public Pais findBySiglas(String siglas) {
        return paisRepository.findPaisBySiglasEquals(siglas);
    }

    @Override
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }
}
