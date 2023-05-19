package com.pablofersep.practicaintegradora.servicios.principales;

import com.pablofersep.practicaintegradora.entidades.principales.Carrito;
import com.pablofersep.practicaintegradora.entidades.principales.Cliente;

public interface CarritoService {

    Carrito findCarritoByEmailCliente(String cliente);
    Carrito crear_modificar(Carrito c);
}
