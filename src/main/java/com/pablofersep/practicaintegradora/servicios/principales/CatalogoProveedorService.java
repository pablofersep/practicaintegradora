package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.CatalogoProveedor;

import java.util.List;

public interface CatalogoProveedorService {

    List<CatalogoProveedor> findAll();
    CatalogoProveedor findById(Long id);
}
