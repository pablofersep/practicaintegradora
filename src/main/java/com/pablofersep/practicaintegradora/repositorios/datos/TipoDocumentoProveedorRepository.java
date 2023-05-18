package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoProveedorRepository extends JpaRepository<TipoDocumentoProveedor, Long> {

    TipoDocumentoProveedor findTipoDocumentoProveedorBySiglasEquals(String siglas);
}