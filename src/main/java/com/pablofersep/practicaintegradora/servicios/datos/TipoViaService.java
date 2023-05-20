package com.pablofersep.practicaintegradora.servicios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.TipoDocumentoCliente;
import com.pablofersep.practicaintegradora.entidades.datos.TipoVia;

import java.util.List;

public interface TipoViaService {
    TipoVia findBySiglas(String siglas);
    List<TipoVia> findAll();
}
