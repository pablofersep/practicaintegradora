package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Producto;

import java.util.List;

public interface ProductoService {

    Producto crear_modificar(Producto p);
    Producto findProductoByCodigo(String codigo);
    List<Producto> findAllByCat(String cat);
    List<Producto> findAll();
}
