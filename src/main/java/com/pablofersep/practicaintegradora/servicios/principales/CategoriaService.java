package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Categoria;

import java.util.List;
import java.util.Set;

public interface CategoriaService {
    Categoria findByCodigo(String codigo);
    boolean existenCategorias(List<String> codigos);
    Set<Categoria> categoriasPorCodigos(List<String> codigos);

    List<Categoria> findAll();
}