package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Pedido;

import java.util.List;

public interface PedidoService {
    List<Pedido> findAll();

    Pedido crear_modificar(Pedido pedido);
    Pedido findById(String siglas);
}
