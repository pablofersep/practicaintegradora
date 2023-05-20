package com.pablofersep.practicaintegradora.servicios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.Genero;
import com.pablofersep.practicaintegradora.entidades.datos.TipoVia;

import java.util.List;

public interface GeneroService {
    Genero findBySiglas(String siglas);
    List<Genero> findAll();
}
