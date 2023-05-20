package com.pablofersep.practicaintegradora.repositorios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Carrito;
import com.pablofersep.practicaintegradora.entidades.principales.CatalogoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoProveedorRepository extends JpaRepository<CatalogoProveedor, Long> {
    CatalogoProveedor findCatalogoProveedorByIdEquals(Long id);
}
