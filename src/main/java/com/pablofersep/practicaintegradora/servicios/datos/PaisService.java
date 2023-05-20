package com.pablofersep.practicaintegradora.servicios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.Pais;
import com.pablofersep.practicaintegradora.entidades.datos.TipoVia;

import java.util.List;

public interface PaisService {
    Pais findBySiglas(String siglas);
    List<Pais> findAll();
}
