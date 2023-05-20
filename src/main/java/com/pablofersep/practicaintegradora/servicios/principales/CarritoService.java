package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Carrito;
import com.pablofersep.practicaintegradora.entidades.principales.Cliente;

import java.util.List;

public interface CarritoService {

    List<Carrito> findAll();
    Carrito findCarritoByEmailCliente(String cliente);
    Carrito crear_modificar(Carrito c);
}
