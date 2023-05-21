package com.pablofersep.practicaintegradora.servicios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.EstadoPedido;

import java.util.List;

public interface EstadoPedidoService {
    EstadoPedido findBySiglas(String siglas);
    List<EstadoPedido> findAll();
}
