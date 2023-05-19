package com.pablofersep.practicaintegradora.servicios.datos;

import com.pablofersep.practicaintegradora.entidades.datos.EstadoPedido;

public interface EstadoPedidoService {
    EstadoPedido findBySiglas(String siglas);
}
