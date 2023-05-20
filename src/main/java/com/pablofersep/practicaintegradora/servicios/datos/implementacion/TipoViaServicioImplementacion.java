package com.pablofersep.practicaintegradora.servicios.datos.implementacion;

import com.pablofersep.practicaintegradora.entidades.datos.TipoVia;
import com.pablofersep.practicaintegradora.repositorios.datos.TipoViaRepository;
import com.pablofersep.practicaintegradora.servicios.datos.TipoViaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoViaServicioImplementacion implements TipoViaService {
    @Autowired
    private TipoViaRepository tipoViaRepository;

    @Override
    public TipoVia findBySiglas(String siglas) {
        return tipoViaRepository.findTipoViasBySiglasEquals(siglas);
    }

    @Override
    public List<TipoVia> findAll() {
        return tipoViaRepository.findAll();
    }
}
