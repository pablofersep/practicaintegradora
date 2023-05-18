package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoTarjetaCreditoRepository extends JpaRepository<TipoTarjetaCredito, Long> {

    TipoTarjetaCredito findTipoTarjetaCreditoBySiglasEquals(String siglas);
}
