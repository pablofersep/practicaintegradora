package com.pablofersep.practicaintegradora.repositorios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Long> {

    EstadoPedido findEstadoPedidoBySiglasEquals(String siglas);
    List<EstadoPedido> findAllByOrderByOrden();
}

