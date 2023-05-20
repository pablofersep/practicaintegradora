package com.pablofersep.practicaintegradora.servicios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.TipoDocumentoCliente;

import java.util.List;

public interface TipoDocumentoClienteService {
    TipoDocumentoCliente findBySiglas(String siglas);
    List<TipoDocumentoCliente> findAll();
}
