package com.pablofersep.practicaintegradora.repositorios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Promocion;
import com.pablofersep.practicaintegradora.entidades.principales.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    Proveedor findProveedorByIdEquals(Long id);
}
