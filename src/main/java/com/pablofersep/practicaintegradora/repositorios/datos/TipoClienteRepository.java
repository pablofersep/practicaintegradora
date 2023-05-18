package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoClienteRepository extends JpaRepository<TipoCliente, Long> {

    TipoCliente findTipoClienteBySiglasEquals(String siglas);
}