package com.pablofersep.practicaintegradora.servicios.datos.implementacion;

import com.pablofersep.practicaintegradora.entidades.datos.TipoDocumentoCliente;
import com.pablofersep.practicaintegradora.repositorios.datos.TipoDocumentoClienteRepository;
import com.pablofersep.practicaintegradora.servicios.datos.TipoDocumentoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoClienteServiceImplementacion implements TipoDocumentoClienteService {
    @Autowired
    private TipoDocumentoClienteRepository tipoDocumentoClienteRepository;

    @Override
    public TipoDocumentoCliente findBySiglas(String s) {
        return tipoDocumentoClienteRepository.findTipoDocumentoClienteBySiglasEquals(s);
    }

    @Override
    public List<TipoDocumentoCliente> findAll() {
        return tipoDocumentoClienteRepository.findAll();
    }
}
