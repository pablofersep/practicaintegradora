package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Proveedor;

import java.util.List;

public interface ProveedorService {
    List<Proveedor> findAll();
    Proveedor findById(Long id);
}
