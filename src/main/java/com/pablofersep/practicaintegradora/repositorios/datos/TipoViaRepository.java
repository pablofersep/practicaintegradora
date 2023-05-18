package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoViaRepository extends JpaRepository<TipoVia, Long> {

    TipoVia findTipoViasBySiglasEquals(String siglas);
}
