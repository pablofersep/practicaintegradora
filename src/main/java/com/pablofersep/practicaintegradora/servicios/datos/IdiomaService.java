package com.pablofersep.practicaintegradora.servicios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.Idioma;
import com.pablofersep.practicaintegradora.entidades.datos.TipoVia;

import java.util.List;

public interface IdiomaService {
    Idioma findBySiglas(String siglas);
    List<Idioma> findAll();
}
