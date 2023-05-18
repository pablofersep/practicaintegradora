package com.pablofersep.practicaintegradora.repositorios.datos;


import com.pablofersep.practicaintegradora.entidades.datos.ClaseProveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseProveedorRepository extends JpaRepository<ClaseProveedor, Long> {

    ClaseProveedor findClaseProveedorBySiglasEquals(String siglas);
}
