package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.TipoDocumentoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoClienteRepository extends JpaRepository<TipoDocumentoCliente, Long> {

    TipoDocumentoCliente findTipoDocumentoClienteBySiglasEquals(String siglas);
}